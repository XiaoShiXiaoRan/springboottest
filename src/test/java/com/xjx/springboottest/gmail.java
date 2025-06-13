package com.xjx.springboottest;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import com.xjx.springboottest.MailUtil; // Import the MailUtil class

public class gmail {
    private static MailUtil mailUtil = new MailUtil(); // Instantiate the MailUtil class

    public static void main(String[] args) {
        gmail();
    }

    public static void gmail() {
        Random random = new Random(); // Create a Random instance
        int i = random.nextInt(900000) + 100000; // Generate a random number
        List<String> mail = new ArrayList<>();
        mail.add("邮箱内容");

        String text = String.valueOf(i); // Convert int to String

        mailUtil.sendMailMessage(mail, "您有一封来自 fidnor.com 的回执！", text);

        System.out.println(mail);
    }
}
