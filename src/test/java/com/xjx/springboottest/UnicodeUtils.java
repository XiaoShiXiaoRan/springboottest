package com.xjx.springboottest;

import java.io.UnsupportedEncodingException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class UnicodeUtils {
    public static void main(String[] args) throws UnsupportedEncodingException {

        //从键盘接收数据
        Scanner scanner = new Scanner(System.in);

        System.out.println("请输入数据:");
        //等待输入数据 按下回车
        //接收数据
        String str = scanner.nextLine();


        //输出数据
        System.out.println("输出的内容为: "+str);

        //中文转Unicode编码
        String unicodeResult = cnToUnicode(str);
        System.out.println("中文转Unicode编码后结果：" + unicodeResult);

        //Unicode编码转中文
        //第一种格式
        String cnResult = unicodeToCN(unicodeResult);
        System.out.println("Unicode编码转中文后结果：" + cnResult);

        //关闭
        scanner.close();
    }

    /**
     * 中文转Unicode
     * 其他英文字母或特殊符号也可进行Unicode编码
     * @param cn
     * @return
     */
    public static String cnToUnicode(String cn) {
        char[] chars = cn.toCharArray();
        StringBuilder returnStr = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            returnStr.append("\\u").append(Integer.toString(chars[i], 16));
        }
        return returnStr.toString();
    }

    /**
     * Unicode转 汉字字符串
     *
     * @param str
     * @return
     */
    public static String unicodeToCN(String str) {
        Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");
        Matcher matcher = pattern.matcher(str);
        char ch;
        while (matcher.find()) {
            ch = (char) Integer.parseInt(matcher.group(2), 16);
            str = str.replace(matcher.group(1), ch + "");
        }
        return str;
    }
}
