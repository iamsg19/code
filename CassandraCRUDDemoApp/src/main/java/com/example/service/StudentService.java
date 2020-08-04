package com.example.service;

import java.util.List;
import java.util.UUID;

import com.example.entity.Student;
import com.example.model.StudentDTO;

public interface StudentService {

	//creating student data
	public Student saveStudent(StudentDTO student) throws Exception;

	//getting all student data
	public List<StudentDTO> allStudent()throws Exception;

	//updating student data
	public boolean updateStudent(String uuid, StudentDTO student)throws Exception;

	//getting student data based on id
	public Student studentById(UUID studentId)throws Exception;

	boolean deleteStudent(UUID studentId) throws Exception;
}
