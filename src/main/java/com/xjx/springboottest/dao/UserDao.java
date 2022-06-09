package com.xjx.springboottest.dao;

import com.xjx.springboottest.po.User;
import com.xjx.springboottest.po.UserVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDao{

    //查询所有用户信息
    public User selectById(Integer userId);

    //查询所有用户以及相关信息
    public List<UserVo> selectUserAll();

    //查询所有部门名称
    public List<UserVo> selectDepartment();

    //查询所有用户组名称 user_group
    public List<UserVo> selectUserGroup();

    //新增用户
    Integer insertUser(User user);

    //修改用户信息
    Integer updateUser(User user);

    //删除用户信息
    Integer deleteById(Integer id);
}
