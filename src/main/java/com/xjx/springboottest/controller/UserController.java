package com.xjx.springboottest.controller;

import com.xjx.springboottest.po.User;
import com.xjx.springboottest.po.UserVo;
import com.xjx.springboottest.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class UserController {

    private static char H = '\n';

    @Autowired
    private IUserService userService;

    @RequestMapping("/index")
    public String index(Model model){
        List<UserVo> users = userService.selectUserAll();

        //System.out.println(users);

        model.addAttribute("users",users);

        return "index";
    }

//    @RequestMapping("/user")
//    public String selectAll(Model model){
//        List<User> users = userService.selectAll();
//        model.addAttribute("users",users);
//        return "table/user";
//    }


    @RequestMapping("/add")
    public String addUserSelect(Model model){
        //查询所有部门信息
        List<UserVo> department = userService.selectDepartment();
        model.addAttribute("department",department);

        //查询所有用户组
        List<UserVo> userGroup = userService.selectUserGroup();
        model.addAttribute("userGroup",userGroup);

        return "table/addUser";
    }

    @RequestMapping("/insertUser")
    @ResponseBody
    public Map<String, String> addUser(@RequestParam("departmentName") Integer departmentName,
                          @RequestParam("userGroupName") Integer userGroupName,
                          @RequestParam("userName") String userName,
                          @RequestParam("userSex") Byte userSex,
                          @RequestParam("idCard") String idCard,
                          @RequestParam("userAddress") String userAddress,
                          @RequestParam("userPhone") String userPhone,
                          @RequestParam("userPassword") String userPassword,
                          @RequestPart("userImage") MultipartFile userImage) throws IOException {

        User user = new User();
        Map<String, String> map = new HashMap<>();

        user.setDepartmentId(departmentName);
        user.setUserGroupId(userGroupName);
        user.setUserName(userName);
        user.setUserSex(userSex);
        user.setIdCard(idCard);
        user.setUserAddress(userAddress);
        user.setUserPhone(userPhone);
        user.setUserPassword(userPassword);

        System.out.println(user);


        //获取jar包所在目录
        ApplicationHome h = new ApplicationHome(getClass());
        File jarF = h.getSource();
        //在jar包所在目录下生成一个upload文件夹用来存储上传的图片
        String dirPath = jarF.getParentFile().toString()+"\\upload";
        System.out.println(dirPath);

        File filePath=new File(dirPath);
        if(!filePath.exists()) {
            filePath.mkdirs();
        }


        if (!userImage.isEmpty()) {
            String originalFilename = userImage.getOriginalFilename();

            //获取最后一个.的位置
            int lastIndexOf = originalFilename.lastIndexOf(".");

            //获取文件的后缀名 .jpg
            String suffix = originalFilename.substring(lastIndexOf);
//                	System.out.println(suffix);
            String imgname = originalFilename.substring(0,lastIndexOf);

            String filePathUp = "pg-" + UUID.randomUUID().toString().replaceAll("-", "") + suffix;
            //==================================


            userImage.transferTo(new File(dirPath +"\\"+ filePathUp));
            user.setUserImage("/upload/" + filePathUp);
            map.put("图片上传状态:", "图片上传成功:"+filePathUp + H);
        } else {
            map.put("图片上传状态:", "图片上传失败" + H);
        }

        try {
            boolean bool = this.userService.insertUser(user);
            if (bool) {
                map.put("新增字段状态:", "新增成功" + H);
            } else {
                map.put("新增字段状态:", "新增失败" + H);
            }
        } catch (Exception e) {
            map.put("新增字段异常状态:", e.getMessage());
        }
        return map;
    }

    //修改回填
    @RequestMapping("/selectById")
    public String selectById(Integer userId,Model model){

        //查询所有部门信息
        List<UserVo> department = userService.selectDepartment();
        model.addAttribute("department",department);

        //查询所有用户组
        List<UserVo> userGroup = userService.selectUserGroup();
        model.addAttribute("userGroup",userGroup);

        //根据id回填用户信息
        User user = this.userService.selectById(userId);

        model.addAttribute("user",user);

        return "table/updateUser";
    }

    //修改
    @RequestMapping("/updateUser")
    @ResponseBody
    public HashMap updateUser(@RequestParam("userId") Integer userId,
                              @RequestParam("departmentName") Integer departmentName,
                              @RequestParam("userGroupName") Integer userGroupName,
                              @RequestParam("userName") String userName,
                              @RequestParam("userSex") Byte userSex,
                              @RequestParam("idCard") String idCard,
                              @RequestParam("userAddress") String userAddress,
                              @RequestParam("userPhone") String userPhone,
                              @RequestParam("userPassword") String userPassword,
                              @RequestPart("userImage") MultipartFile userImage) throws IOException {
        HashMap<String, String> map = new HashMap<>();
        User user = new User();

        user.setUserId(userId);
        user.setDepartmentId(departmentName);
        user.setUserGroupId(userGroupName);
        user.setUserName(userName);
        user.setUserSex(userSex);
        user.setIdCard(idCard);
        user.setUserAddress(userAddress);
        user.setUserPhone(userPhone);
        user.setUserPassword(userPassword);


        //获取jar包所在目录
        ApplicationHome h = new ApplicationHome(getClass());
        File jarF = h.getSource();

        //在jar包所在目录下生成一个upload文件夹用来存储上传的图片
        String dirPath = jarF.getParentFile().toString()+"\\upload";
        File filePath=new File(dirPath);
        if(!filePath.exists()) {
            filePath.mkdirs();
        }

        if (!userImage.isEmpty()) {
            String originalFilename = userImage.getOriginalFilename();
            //获取最后一个.的位置
            int lastIndexOf = originalFilename.lastIndexOf(".");
            //获取文件的后缀名 .jpg
            String suffix = originalFilename.substring(lastIndexOf);

            String imgname = originalFilename.substring(0,lastIndexOf);
            //抛IO异常
            String filePathUp = "pg-" + UUID.randomUUID().toString().replaceAll("-", "") + suffix;

            userImage.transferTo(new File(dirPath +"\\"+ filePathUp));
            user.setUserImage("/upload/" + filePathUp);
            map.put("图片修改状态:","图片修改成功" + H);

            //删除原有图片
            User userimg = this.userService.selectById(userId);
            //获取文件的后缀名 .jpg
            String userImageNeme = userimg.getUserImage().substring(8);
            if (!userImage.isEmpty()) {

                //到上面截取的路径+路径名下去看，是否有这个图片
                File targetFile = new File(dirPath,userImageNeme);
                //如果存在，就删除
                if(targetFile.exists()) {
                    boolean isDelete = targetFile.delete();
                    if (isDelete) {
                        map.put("原图片状态:", "原图片删除成功" + H);
                    } else {
                        map.put("原图片状态:", "原图片删除失败" + H);
                    }
                }
            }

        }
        boolean b = this.userService.updateUser(user);
        if (!b){
            map.put("修改状态:","修改失败" + H);
        }
        map.put("修改状态:","修改成功:user="+user.toString() + H);
        return map;
    }
    //删除        ,@RequestPart("userImage") MultipartFile userImage
    @RequestMapping("/deleteById")
    @ResponseBody
    public HashMap deleteById(@RequestParam("id") Integer id){

        HashMap<String , String> map = new HashMap<>();

        User user = this.userService.selectById(id);

        boolean b = this.userService.deleteById(id);

        if (!b){
            map.put("删除状态","删除失败" + H);
        }
        //获取文件的后缀名 .jpg
        String userImage = user.getUserImage().substring(8);
        if (!userImage.isEmpty()) {
            //获取jar包所在目录
            ApplicationHome h = new ApplicationHome(getClass());
            File jarF = h.getSource();
            //在jar包所在目录下生成一个upload文件夹用来存储上传的图片
            String dirPath = jarF.getParentFile().toString()+"\\upload";

            //到上面截取的路径+路径名下去看，是否有这个图片
            File targetFile = new File(dirPath,userImage);
            //如果存在，就删除
            if(targetFile.exists()) {
                boolean isDelete = targetFile.delete();
                if (isDelete) {
                    map.put("图片状态:", "图片删除成功" + H);
                } else {
                    map.put("图片状态:", "图片删除失败" + H);
                }
            }
        }
        map.put("删除状态","删除成功" + H);
        return map;
    }
}
