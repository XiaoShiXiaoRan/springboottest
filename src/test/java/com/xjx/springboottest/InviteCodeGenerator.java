package com.xjx.springboottest;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.UUID;

public class InviteCodeGenerator {

    public static String generateInviteCode(String userId) {
        try {
            // 生成一个随机的UUID
            UUID uuid = UUID.randomUUID();

            // 将userId与UUID结合
            String source = userId + uuid.toString();

            // 使用SHA-256哈希获取散列值
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(source.getBytes(StandardCharsets.UTF_8));

            // 对结果进行Base64编码，然后保留前8个字符
            String encoded = Base64.getUrlEncoder().encodeToString(hash);

            // 只保留前8位
            return encoded.substring(0, 8);

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error occurred while generating invite code", e);
        }
    }

    public static void main(String[] args) {
        String userId = "1836966841507938306"; // String类型的userId
        String inviteCode = generateInviteCode(userId);
        System.out.println("邀请码: " + inviteCode);
    }
}
