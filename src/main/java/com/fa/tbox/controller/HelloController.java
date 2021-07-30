package com.fa.tbox.controller;

import cn.hutool.crypto.symmetric.AES;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;

@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String hello(String name, String pwdString) {

        AES aes = new AES("1234567890123456".getBytes());

        String s = aes.decryptStr(pwdString, StandardCharsets.UTF_8);
        if (StringUtils.equals(name, s)) {


            return aes.encryptHex("hello");
        }

        return aes.encryptHex("hellq");
    }
}
