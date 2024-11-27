package com.mcz.utils;


import org.springframework.security.crypto.bcrypt.BCrypt;

public class BCryptUtil {

    /**
     * 密码加密
     * @param rawPassword 原始密码
     * @return 加密后的密码
     */
    public static String hashPassword(String rawPassword) {
        return BCrypt.hashpw(rawPassword, BCrypt.gensalt());
    }

    /**
     * 验证密码是否匹配
     * @param rawPassword 原始密码
     * @param hashedPassword 加密后的密码
     * @return 密码是否匹配
     */
    public static boolean checkPassword(String rawPassword, String hashedPassword) {
        return BCrypt.checkpw(rawPassword, hashedPassword);
    }
}