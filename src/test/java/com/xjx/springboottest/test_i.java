package com.xjx.springboottest;


import java.util.Random;

public class test_i {
    public static void main(String[] args) {


        Random ran = new Random();
        for (int i=0;i<10;i++){
            double val = ran.nextDouble()*20;
            String  str22 = String.format("%.1f",val);
            double four22 = Double.parseDouble(str22);
//            System.out.println(four22);

            int a =  (int)(Math.random()*(100000)+300000);
            System.out.println(a);
        }


    }
}
