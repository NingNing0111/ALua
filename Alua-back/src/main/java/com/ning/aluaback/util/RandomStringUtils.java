package com.ning.aluaback.util;

import java.util.Random;

/**
 * @Project: com.ning.aluaback.util
 * @Author: pgthinker
 * @Date: 2023/12/26 23:51
 * @Description: 随机字符串生成工具
 */
public class RandomStringUtils {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    public static String generateRandomString(int length) {
        StringBuilder sb = new StringBuilder(length);
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(index);
            sb.append(randomChar);
        }

        return sb.toString();
    }
}
