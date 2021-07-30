package com.fa.tbox.util;


import cn.hutool.crypto.symmetric.AES;
import org.junit.jupiter.api.Test;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class AESTest {

    @Test
    public void test() throws UnsupportedEncodingException {
        byte[] key1 = "1234567890123456".getBytes();
        byte[] key2 = "1234567890123456".getBytes("UTF-8");
        AES aes = new AES(key1);
//        aes.setIv(new IvParameterSpec(new byte[16]));
        byte[] nis = aes.encrypt("ni");
        String nis2 = aes.encryptHex("ni");
        System.out.println(nis2);
        System.out.println(aes.getCipher().getAlgorithm());
        System.out.println(aes.getSecretKey().getFormat());
        System.out.println(aes.getSecretKey().getAlgorithm());

        System.out.println(aes.decryptStr(nis));
        System.out.println(aes.decryptStr("ebb7c703e675db3da397038b4c17823c"));
        System.out.println(aes.decryptStr(nis2, StandardCharsets.UTF_8));
    }
}
