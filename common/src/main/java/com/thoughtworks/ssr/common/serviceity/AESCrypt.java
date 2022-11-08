package com.thoughtworks.ssr.common.serviceity;

import com.thoughtworks.ssr.common.exception.ScpException;
import org.apache.commons.codec.binary.Base64;
import org.springframework.util.ObjectUtils;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Logger;

public class AESCrypt {

    private static final Logger LOG = Logger.getLogger("AESCrypt");

    private static final String iv = "1234567812347890";
    // 16位口令
    private static final String key = "thoughtworks.com";


    public static String encrypt(String str) {
        if (ObjectUtils.isEmpty(str)) {
            return str;
        }

        try {
            var raw = key.getBytes(StandardCharsets.UTF_8);
            var secretKeySpec = new SecretKeySpec(raw, "AES");
            var cipher = Cipher.getInstance("AES/CBC/Iso10126Padding");//"算法/模式/补码方式"
            var ivParameterSpec = new IvParameterSpec(iv.getBytes(StandardCharsets.UTF_8));

            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);

            var encrypted = cipher.doFinal(str.getBytes(StandardCharsets.UTF_8));

            return new String(Base64.encodeBase64(encrypted), StandardCharsets.UTF_8);

        } catch (BadPaddingException | IllegalBlockSizeException | InvalidKeyException |
                 InvalidAlgorithmParameterException | NoSuchAlgorithmException | NoSuchPaddingException e) {
            e.printStackTrace();
            LOG.severe("AESCrypt Encrypt Error !");
            throw new ScpException("AESCrypt Encrypt Error !");
        }
    }


    public static String decrypt(String str) {
        if (ObjectUtils.isEmpty(str)) {
            return str;
        }

        try {
            var raw = key.getBytes(StandardCharsets.UTF_8);
            var secretKeySpec = new SecretKeySpec(raw, "AES");
            var cipher = Cipher.getInstance("AES/CBC/Iso10126Padding");
            var ivParameterSpec = new IvParameterSpec(iv.getBytes(StandardCharsets.UTF_8));

            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);

            var encrypted1 = Base64.decodeBase64(str.getBytes(StandardCharsets.UTF_8));//先用bAES6

            var original = cipher.doFinal(encrypted1);
            return new String(original, StandardCharsets.UTF_8);

        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException |
                 InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
            LOG.severe("AESCrypt Decrypt Error !");
            throw new ScpException("AESCrypt Decrypt Error !");
        }
    }

}
