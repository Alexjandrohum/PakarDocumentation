package com.scpakar.scpakarweb.util;

import java.io.Serializable;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import mx.com.pakar.util.Util;
import org.apache.commons.net.util.Base64;

/**
 *
 * @author pablo.martinez
 */
public class AESCrypt implements Serializable {

    private static final String ALGORITHM = "AES";
    private static final String KEY = "1Hbfh667adfDEJ78";

    public static String encrypt(String value) throws Exception {
        Key key = generateKey();
        Cipher cipher = Cipher.getInstance(AESCrypt.ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedByteValue = cipher.doFinal(value.getBytes("utf-8"));
        return Base64.encodeBase64String(encryptedByteValue);
    }

    public static String decrypt(String value) throws Exception {
        try {
            Key key = generateKey();
            Cipher cipher = Cipher.getInstance(AESCrypt.ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] decryptedValue64 = Base64.decodeBase64(value);
            byte[] decryptedByteValue = cipher.doFinal(decryptedValue64);
            return new String(decryptedByteValue, "utf-8");
        } catch (Exception ex) {
            Util.registraError(ex);
        }
        return "";
    }

    private static Key generateKey() throws Exception {
        return new SecretKeySpec(AESCrypt.KEY.getBytes(), AESCrypt.ALGORITHM);
    }

    public static String encryptMD5(String value) throws Exception {
        String md5 = "";
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
            digest.update(value.getBytes());
            byte messageDigest[] = digest.digest();
            md5 = Base64.encodeBase64String(messageDigest).trim();
        } catch (NoSuchAlgorithmException ex) {
            Util.registraError(ex);
        } catch (Error x) {
            Util.registraError(x);
        } finally {
            return md5;
        }
    }
}
