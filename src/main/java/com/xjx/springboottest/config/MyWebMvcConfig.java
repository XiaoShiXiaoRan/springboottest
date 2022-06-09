package com.xjx.springboottest.config;

import org.springframework.boot.system.ApplicationHome;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Configuration
public class MyWebMvcConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //获取java包所在目录
        ApplicationHome h = new ApplicationHome(getClass());
        File jarF = h.getSource();
        //在jar包所在目录下生成一个upload文件夹用来储存上传的图片
        String dirPath = jarF.getParentFile().toString()+"/upload/";

        registry.addResourceHandler("/upload/**").addResourceLocations("file:/"+dirPath);
    }

    /**
     * 覆写父类方法，增加修改StringHttpMessageConvert默认配置，使返回前台的字符为UTF-8
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(stringHttpMessageConverter());
    }
    //自定义类：创建HttpMessage返回值编码转换类
    public HttpMessageConverter<String> stringHttpMessageConverter(){
        StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter(StandardCharsets.UTF_8);
        return stringHttpMessageConverter;
    }
}
