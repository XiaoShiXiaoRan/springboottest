package com.xjx.springboottest.service;

import com.xjx.springboottest.po.User;
import com.xjx.springboottest.po.UserVo;

import java.util.List;

public interface IUserService {
    User selectById(Integer userId);
    List<UserVo> selectUserAll();
    List<UserVo> selectDepartment();
    List<UserVo> selectUserGroup();
    boolean insertUser(User user);
    boolean updateUser(User user);
    boolean deleteById(Integer id);
}
