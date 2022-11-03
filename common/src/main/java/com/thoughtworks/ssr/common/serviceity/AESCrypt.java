package com.thoughtworks.ssr.common.serviceity;

import com.thoughtworks.ssr.common.exception.ScpException;
import org.springframework.util.ObjectUtils;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.logging.Logger;

public class AESCrypt {

    private static final Logger LOG = Logger.getLogger("AESCrypt");

    private static final String key = "ssr.thoughtworks.com";

    private static Cipher cipher;

    static {
        try {
            cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key.getBytes(), "AES"));
        } catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException e) {
            LOG.severe("Init keySpec error !");
        }
    }

    public static String encrypt(String str) {
        if (ObjectUtils.isEmpty(str)) {
            return str;
        }
        try {
            return Base64.getEncoder().encodeToString(cipher.doFinal(str.getBytes()));
        } catch (IllegalBlockSizeException | BadPaddingException e) {
            throw new ScpException("AESCrypt Encrypt Error !");
        }
    }

    public static String decrypt(String str) {
        if (ObjectUtils.isEmpty(str)) {
            return str;
        }

        try {
            var decrypt = cipher.doFinal(Base64.getDecoder().decode(str));
            return new String(decrypt);
        } catch (IllegalBlockSizeException | BadPaddingException e) {
            throw new ScpException("AESCrypt Decrypt Error !");
        }
    }

}
