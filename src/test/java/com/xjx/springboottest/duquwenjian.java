package com.xjx.springboottest;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class duquwenjian {

    @Test
    public void test() throws IOException {

//        this.getWenJianName();

        this.deleteFiles();

    }

    public void getWenJianName(){
            String path = "D:/Sustainability/yingshiyun"; // 路径
            File f = new File(path);//获取路径  F:测试目录
            if (!f.exists()) {
                System.out.println(path + "not exists");//不存在就输出
//                return null;
            }

            File fa[] = f.listFiles();//用数组接收

            List fileNameList = new ArrayList();

            for (int i = 0; i < fa.length; i++) {//循环遍历
                File fs = fa[i];//获取数组中的第i个
                if (fs.isDirectory()) {
//                    System.out.println(fs.getName() + " [目录]");//如果是目录就输出
                } else {
//                    System.out.println(fs.getName());//否则直接输出
                    fileNameList.add(fs.getName());
                }
            }
            System.out.println(fileNameList);

    }

    public void deleteFiles() throws IOException {

        String filePath = "D:/Sustainability/yingshiyun/";

        File folder = new File(filePath);

        FileUtils.cleanDirectory(folder);
    }
}
