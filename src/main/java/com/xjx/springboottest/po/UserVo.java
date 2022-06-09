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
public class UserVo implements Serializable {
//    SELECT * FROM USER LEFT JOIN department ON user.department_id = department.department_id LEFT JOIN user_group ON user.user_group_id = user_group.user_group_id

    //user
    public int userId;
    public String userName;
    public int userSex;
    public String idCard;
    public String userAddress;
    public String userPhone;
    public String userImage;
    public String userPassword;
    public int userGroupId;
    public int departmentId;

    //department         department_id  department_name  department_remark  parent_id
//    private int departmentId;
    public String departmentName;
    public String departmentRemark;
    public int parentId;

    //user_group  user_group_id  user_group_name  remark

    public String userGroupName;
    public String remark;
}
