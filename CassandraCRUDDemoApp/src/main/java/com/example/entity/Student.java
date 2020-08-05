package com.example.entity;

import java.util.UUID;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table(value = "Student")
public class Student {

	@PrimaryKey("stu_id")
	private UUID stuId;
	
	@Column(value = "stu_name")
	private String stuName;
	
	@Column(value = "stu_age")
	private int stuAge;

	
	public Student() {

	}

	public Student(int stuAge, String stuName, UUID stuId) {
		super();
		this.stuId = stuId;
		this.stuName = stuName;
		this.stuAge = stuAge;
	}

	public UUID getStuId() {
		return stuId;
	}

	public void setStuId(UUID stuId) {
		this.stuId = stuId;
	}

	public String getStuName() {
		return stuName;
	}

	public void setStuName(String stuName) {
		this.stuName = stuName;
	}

	public int getStuAge() {
		return stuAge;
	}

	public void setStuAge(int stuAge) {
		this.stuAge = stuAge;
	}
	

	@Override
	public String toString() {
		return "Student [stuId=" + stuId + ", stuName=" + stuName + ", stuAge=" + stuAge + "]";
	}
}
