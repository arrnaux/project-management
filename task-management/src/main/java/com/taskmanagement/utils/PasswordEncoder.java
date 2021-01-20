package com.taskmanagement.utils;

import java.security.spec.KeySpec;
import java.util.Arrays;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class PasswordEncoder
{
    public final static byte[] salt = "EncryptionKey093".getBytes();

    public static String hashPassword(String password, byte[] salt) throws Exception{
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
        SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] hash = f.generateSecret(spec).getEncoded();
        return Arrays.toString(hash);
    }

}
