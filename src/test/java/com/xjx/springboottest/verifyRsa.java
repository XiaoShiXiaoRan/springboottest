package com.xjx.springboottest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class verifyRsa {
    @Autowired
    private RestTemplate restTemplate;
    @Test
    private void verifyRsa() throws Exception {
        final RestTemplate f = this.restTemplate;

        ExecutorService exec = Executors.newFixedThreadPool(50);
        long start = System.currentTimeMillis();

        System.out.println("kaishi");
        for (int i = 0; i < 100; i++){
            exec.execute(new Runnable() {
                @Override
                public void run() {
                    String sort = "{'lon':116.37304,'lat':39.92594,'ver':1}";
                    String url = "http://api.tianditu.gov.cn/geocoder?postStr={sort}&tk=62fdd7c2bb3b54ddf79163867d9ce29f&type=geocode";
                    long start = System.currentTimeMillis();
                    ResponseEntity<String> responseEntity = f.getForEntity(url, String.class,sort);
                    long time = System.currentTimeMillis() - start;
                    System.out.println("每一次多少秒..............."+time);
                }
            });
        }
        exec.shutdown();
        exec.awaitTermination(1, TimeUnit.HOURS); // 或者更长时间（这行代码是关键）
        long time = System.currentTimeMillis() - start;
        System.out.println("sdfsdfsdfsdf..............."+time);



    }

}
