package com.xjx.springboottest;
import com.xjx.springboottest.entity.Student;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class studentSort {
	public static void main(String[] args) {
	    //初始化学生类
		Student[] stu = {new Student("zhangsan", 20, 88),new Student("lisi", 22, 74),new Student("wangwu", 19, 83),new Student("zhaoliu", 16, 96),new Student("maqi", 25, 65)};
		//打印开始顺序
		System.out.println("开始顺序为：");
		List<Student> list = Arrays.asList(stu);
		list.forEach((student) -> {
			System.out.println("姓名：" + student.getName() + "     年龄：" + student.getAge() + "   分数：" + student.getScore());
		});
		//按照（double类型数据）排序
		Collections.sort(list);
        //打印排序后的顺序
		System.out.println("按照年龄排序后：");
		list.forEach((student) -> {
			System.out.println("姓名：" + student.getName() + "     年龄：" + student.getAge() + "   分数：" + student.getScore());
		});
	}
}
