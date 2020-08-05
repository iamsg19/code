package com.example.model;

import java.util.UUID;

public class StudentDTO {

	private String stuId;
	private String stuName;
	private int stuAge;
	public StudentDTO(int i, String string, String uuid) {
		
		this.stuAge = i;
		this.stuId = "skfjsj";
		this.stuName = "Shivaji";
	}
	public String getStuId() {
		return stuId;
	}
	public void setStuId(String stuId) {
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
		return "StudentDTO [stuId=" + stuId + ", stuName=" + stuName + ", stuAge=" + stuAge + "]";
	}
	
}
