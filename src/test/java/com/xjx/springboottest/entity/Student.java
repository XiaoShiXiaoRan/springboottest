package com.xjx.springboottest.entity;

public class Student implements Comparable<Student>{
	private String name;
	private int age;
	private double score;
	public Student(String name, int age, double score) {
		super();
		this.name = name;
		this.age = age;
		this.score = score;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	//按照单个属性（double数据类型）排序
	   @Override
	    public int compareTo(Student stu) {
	        return  stu.getScore() - this.getScore() > 0 ? 1 : ((this.getScore() == stu.getScore()) ? 0 :-1);   //降序：返回值为1 或-1 升序改变变量位置即可
	    }

}
