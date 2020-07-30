package com.example.model;

public class StudentDTO {

	private String stuId;
	private String stuName;
	private int stuAge;
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
