package com.fa.tbox.util;

import cn.hutool.core.util.HexUtil;
import org.junit.jupiter.api.Test;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

public class EncryptTest {

    @Test
    public void testEncrypt() throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        Key key = new SecretKeySpec("1234567890123456".getBytes(StandardCharsets.UTF_8), "AES");
//        IvParameterSpec iv = new IvParameterSpec(new byte[16]);
//        cipher.init(Cipher.ENCRYPT_MODE,key,iv);
        cipher.init(Cipher.ENCRYPT_MODE,key);
        byte[] testString = "ni".getBytes(StandardCharsets.UTF_8);
        byte[] encrypted = cipher.doFinal(testString);
//        System.out.println("Encrypt Hex: "+Hex.encodeHexString(encrypted));
        System.out.println(HexUtil.encodeHex(encrypted));
    }
}
