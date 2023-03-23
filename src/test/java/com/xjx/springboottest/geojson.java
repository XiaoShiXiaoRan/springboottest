package com.xjx.springboottest;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xjx.springboottest.entity.PropertiesVo;

import java.io.*;
import java.util.*;

public class geojson {

    public static void main(String[] args) {

        PropertiesVo propertiesVo = geojson.getData();

        System.out.println(propertiesVo);

    }

    public static PropertiesVo getData(){
        // 读取文件数据
        //System.out.println("读取文件数据util");
        StringBuffer strbuffer = new StringBuffer();
        File myFile = new File("E:\\upload\\house_4326.json");//"D:"+File.separatorChar+"DStores.json"
        if (!myFile.exists()) {
            System.err.println("Can't Find " + "E:\\upload\\house_4326.json");
        }
        try {
            FileInputStream fis = new FileInputStream("E:\\upload\\house_4326.json");
            InputStreamReader inputStreamReader = new InputStreamReader(fis, "UTF-8");
            BufferedReader in  = new BufferedReader(inputStreamReader);
            String str;
            while ((str = in.readLine()) != null) {
                strbuffer.append(str);  //new String(str,"UTF-8")
            }
            in.close();
        } catch (IOException e) {
            e.getStackTrace();
        }
        //System.out.println("读取文件结束util");
        System.out.println(strbuffer.toString());

        JSONObject object = new JSONObject();

        JSONObject ObjData =  JSONObject.parseObject(strbuffer.toString());

        //获取基本数据
        Object features = ObjData.get("features");

        JSONArray array = JSONArray.parseArray(features.toString());

        //人员 信息
        List list = new ArrayList();


        for (int i = 0; i < array.size(); i++) {

            JSONObject item = array.getJSONObject(i);
            item.get("properties");
            list.add(item.get("properties"));

        }

        //地理信息
        List list2 = new ArrayList();

        for (int i = 0; i < array.size(); i++) {

            JSONObject item = array.getJSONObject(i);
            item.get("geometry");
            list2.add(item.get("geometry"));
        }


        PropertiesVo propertiesa = new PropertiesVo();
        for (int j = 0; j < list.size(); j++) {

            JSONObject propertiesDataa =  JSONObject.parseObject(list.get(j).toString());

            propertiesa.setHouse_name(propertiesDataa.get("house_name").toString());
            propertiesa.setHouse_code_1(propertiesDataa.get("house_code_1").toString());
            propertiesa.setHouse_serial(((Integer) propertiesDataa.get("house_serial")));
            propertiesa.setUserID((Integer) propertiesDataa.get("UserID"));

//            System.out.println(propertiesa);

            JSONObject geometryDataa =  JSONObject.parseObject(list2.get(j).toString());

            //地理坐标 数组
            propertiesa.setCoordinates(geometryDataa.toString());

//            System.out.println(propertiesa.);
        }
        return propertiesa;
    }

}
