package com.xjx.springboottest;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class test_i {
    public static void main(String[] args) {



        List list = new ArrayList();

        for (int i = 87; i <136 ; i++) {
            list.add(i);
        }
        //仓库上传测试
        System.out.println(list.toString());
        System.out.println(list.size());
    }
}
