package com.fa.tbox.util;

import org.apache.tomcat.util.buf.HexUtils;

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

/**
 * 签名工具
 */
public class SignUtil {

    public static void main(String[] args) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        String sign = sign();
        System.out.println("sign: " + sign);
        System.out.println(checkSign(sign));
    }

    private static final String key = "FLY_AUDIO1234567";


    public static String sign() throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        return aesEncrypt();
    }

    public static boolean checkSign(String sign) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        String s = aesDecrypt(sign);

        Long requestTimestamp = Long.valueOf(s);

        long l = System.currentTimeMillis() - requestTimestamp;
        System.out.println("l: " + l);

        if (l > 2000)
            return true;
        return false;
    }

    private static String aesEncrypt() throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");
        IvParameterSpec iv = new IvParameterSpec(new byte[16]);
//        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec,iv);
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        String timestamp = String.valueOf(System.currentTimeMillis());
        byte[] rawData = timestamp.getBytes(StandardCharsets.UTF_8);
        byte[] encryptedBytes = cipher.doFinal(rawData);
        String encryptedString = HexUtils.toHexString(encryptedBytes);

        System.out.println(timestamp + " 加密=> " + encryptedString);
        return encryptedString;
    }


    private static String aesDecrypt(String encryptedString) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);

        byte[] encryptedBytes = encryptedString.getBytes(StandardCharsets.UTF_8);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        String decryptedString = HexUtils.toHexString(decryptedBytes);

        System.out.println(encryptedString + " 解密=> " + decryptedString);
        return decryptedString;
    }


}
