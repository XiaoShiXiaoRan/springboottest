package com.xjx.springboottest.service.impl;

import com.xjx.springboottest.dao.UserDao;
import com.xjx.springboottest.po.User;
import com.xjx.springboottest.po.UserVo;
import com.xjx.springboottest.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    UserDao userDao;

    @Override
    public User selectById(Integer userId) {
        return this.userDao.selectById(userId);
    }

    @Override
    public List<UserVo> selectUserAll() {
        return this.userDao.selectUserAll();
    }

    @Override
    public List<UserVo> selectDepartment() {
        return this.userDao.selectDepartment();
    }

    @Override
    public List<UserVo> selectUserGroup() {
        return this.userDao.selectUserGroup();
    }

    @Override
    public boolean insertUser(User user) {
        //新增
        boolean isOk=this.userDao.insertUser(user)>0;
        if (!isOk){
            throw new RuntimeException("新增操作失败");
        }
        return true;
    }

    @Override
    public boolean updateUser(User user) {

        boolean isOk = this.userDao.updateUser(user)>0;

        if (!isOk){
            throw new RuntimeException("修改操作失败: "+user);
        }

        return true;
    }

    @Override
    public boolean deleteById(Integer id) {
        boolean isOk = this.userDao.deleteById(id)>0;
        if (!isOk){
            throw new RuntimeException("删除操作失败: "+id);
        }
        return true;
    }
}
