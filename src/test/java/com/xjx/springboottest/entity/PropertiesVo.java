package com.xjx.springboottest.entity;

import lombok.Data;

@Data
public class PropertiesVo {

    Integer UserID;
    Integer house_serial;
    String house_code_1;
    String house_name;

    String coordinates;
    String type;
}
