package com.xjx.springboottest;

import cn.hutool.core.util.RandomUtil;

import java.util.Random;
import java.util.UUID;

/**
 *
 * 现总结几种生成一个唯一值的方式
 * 第一种：采用nanoTime()
 *
 */
public class AppIdDemo {



    public static void main(String[] args) {

//        String AppId =null;
//        AppId= "T_GIS_"+System.currentTimeMillis();
//        System.out.println(AppId);
        System.out.println("第一种：采用nanoTime():"+nanoTime());

        System.out.println("第二种：采用UUID类:"+UUID());

    }


    public static String nanoTime(){
        // 理论上存在重复的可能，可以在后面再加上一个随机字符串
        Random r = new Random();
        String n = System.nanoTime() + "" + r.nextInt();
        n.replace("-", "");
        return n;

    }

    public static String UUID(){
        // 第二种:采用UUID类
        String n = UUID.randomUUID().toString();
        n.replace("-", "");
        return n;
    }


}
