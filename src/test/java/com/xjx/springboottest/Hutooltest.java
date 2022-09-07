package com.xjx.springboottest;


import cn.hutool.core.convert.Convert;

import java.util.Scanner;

public class Hutooltest {
    public static void main(String[] args) {

        //从键盘接收数据
        Scanner scanner = new Scanner(System.in);

        System.out.println("请输入数据:");
        //等待输入数据 按下回车
        //接收数据
        String str = scanner.nextLine();

        String unicode = Convert.strToUnicode(str);

        System.out.println("转换为unicode:"+unicode);

        String raw = Convert.unicodeToStr(unicode);
        System.out.println("转换为中文:"+raw);

        //关闭
        scanner.close();
    }
}
