package com.kerry.gogobasketball;

import java.security.SecureRandom;
import java.util.Locale;
import java.util.Objects;
import java.util.Random;

public class RandomString {

    static final String mRandom = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    static SecureRandom sRandom = new SecureRandom();

    public RandomString(){}

    public static String getRandom(int len) {
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++)
            sb.append(mRandom.charAt(sRandom.nextInt(mRandom.length())));
        return sb.toString();
    }
}