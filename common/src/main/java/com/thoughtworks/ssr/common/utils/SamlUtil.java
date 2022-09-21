package com.thoughtworks.ssr.common.utils;

import lombok.extern.slf4j.Slf4j;
import net.shibboleth.utilities.java.support.security.impl.RandomIdentifierGenerationStrategy;
import org.opensaml.core.config.InitializationException;
import org.opensaml.core.config.InitializationService;
import org.opensaml.core.xml.XMLObject;
import org.opensaml.core.xml.config.XMLObjectProviderRegistrySupport;
import org.opensaml.core.xml.io.Unmarshaller;
import org.opensaml.core.xml.io.UnmarshallerFactory;
import org.opensaml.saml.saml2.core.Assertion;
import org.opensaml.saml.saml2.core.EncryptedAssertion;
import org.opensaml.saml.saml2.core.Response;
import org.opensaml.saml.saml2.encryption.Decrypter;
import org.opensaml.saml.security.impl.SAMLSignatureProfileValidator;
import org.opensaml.security.x509.BasicX509Credential;
import org.opensaml.xmlsec.encryption.support.DecryptionException;
import org.opensaml.xmlsec.encryption.support.InlineEncryptedKeyResolver;
import org.opensaml.xmlsec.keyinfo.impl.StaticKeyInfoCredentialResolver;
import org.opensaml.xmlsec.signature.Signature;

import java.nio.charset.StandardCharsets;
import java.security.cert.X509Certificate;

import org.opensaml.xmlsec.signature.support.SignatureException;
import org.opensaml.xmlsec.signature.support.SignatureValidator;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
public class SamlUtil {
    private static RandomIdentifierGenerationStrategy secureRandomIdGenerator;

    static {
        try {
            InitializationService.initialize();
            secureRandomIdGenerator = new RandomIdentifierGenerationStrategy();
        } catch (InitializationException e) {
            e.printStackTrace();
        }
    }

    public static String generateSecureRandomId() {
        return secureRandomIdGenerator.generateIdentifier();
    }

    public static String base64Decode(String stringToDecode) {
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] decodedByte = decoder.decode(stringToDecode);
        return new String(decodedByte);
    }

    public static boolean validateSamlSignature(String saml, BasicX509Credential samlPublicKeyCredential) {
        Response samlResponse = SamlUtil.getSamlResponse(saml);
        log.info(samlResponse.toString());

        List<Assertion> decryptedAssertions = decryptSaml(samlResponse, samlPublicKeyCredential).orElse(new ArrayList<>());
        decryptedAssertions.forEach(assertion -> {
            if (assertion.isSigned()) {
                System.out.println(assertion.toString());
            }
        });

        boolean valid = false;
        if (samlResponse.isSigned()) {
            SAMLSignatureProfileValidator profileValidator = new SAMLSignatureProfileValidator();
            Signature signature = samlResponse.getSignature();

            try {
                profileValidator.validate(signature);
                SignatureValidator.validate(signature, samlPublicKeyCredential);
                valid = true;
            } catch (SignatureException e) {
                e.printStackTrace();
            }
        }
        return valid;
    }

    public static BasicX509Credential publicKeyCredential(String samlCerPath) throws IOException, CertificateException {
        try (FileInputStream input = new FileInputStream(new File(samlCerPath))) {
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            return new BasicX509Credential((X509Certificate) cf.generateCertificate(input));
        }
    }

    private static Optional<List<Assertion>> decryptSaml(Response samlResponse, BasicX509Credential publicKeyCredential) {
        StaticKeyInfoCredentialResolver keyInfoCredentialResolver = new StaticKeyInfoCredentialResolver(publicKeyCredential);

        Decrypter decrypter = new Decrypter(null, keyInfoCredentialResolver, new InlineEncryptedKeyResolver());
        decrypter.setRootInNewDocument(true);

        List<EncryptedAssertion> assertions = samlResponse.getEncryptedAssertions();
        if (assertions != null) {
            return Optional.of(assertions.stream()
                    .map(assertion -> {
                        try {
                            return decrypter.decrypt(assertion);
                        } catch (DecryptionException e) {
                            e.printStackTrace();
                        }
                        return null;
                    })
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList()));
        }
        return Optional.empty();
    }


    private static Response getSamlResponse(String saml) {
        Response response = null;
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            documentBuilderFactory.setNamespaceAware(true);
            DocumentBuilder docBuilder = documentBuilderFactory.newDocumentBuilder();
            UnmarshallerFactory unmarshallerFactory = XMLObjectProviderRegistrySupport.getUnmarshallerFactory();
            Document document = docBuilder.parse(new ByteArrayInputStream(saml.getBytes(StandardCharsets.UTF_8)));

            Element element = document.getDocumentElement();
            Unmarshaller unmarshaller = unmarshallerFactory.getUnmarshaller(element);
            XMLObject responseXmlObj = unmarshaller.unmarshall(element);
            response = (Response) responseXmlObj;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }
}
