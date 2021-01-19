package com.ron.ssm.utills;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.UUID;

public class BCryptPasswordEncoderUtills {

    private static BCryptPasswordEncoder  encoder = new BCryptPasswordEncoder();
    public static String encodePassword(String password){
        String encode = encoder.encode(password);
        return encode;
    }

    public static void main(String[] args) {
        String password = "123";

        String s = encodePassword(password);
        System.out.println(s);


        String replace = UUID.randomUUID().toString().replace("-", "");

        System.out.println(replace);
    }
}
