package com.xjx.springboottest.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User implements Serializable {
    //user_id  user_name  user_sex  id_card
    // user_address  user_phone   user_image  user_password  user_group_id  department_id
    private int userId;
    private String userName;
    private int userSex;
    private String idCard;
    private String userAddress;
    private String userPhone;
    private String userImage;
    private String userPassword;
    private int userGroupId;
    private int departmentId;
}
