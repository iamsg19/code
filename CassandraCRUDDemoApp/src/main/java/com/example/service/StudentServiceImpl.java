package com.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.datastax.oss.driver.api.core.uuid.Uuids;
import com.example.entity.Student;
import com.example.model.StudentDTO;
import com.example.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService{

	@Autowired
	private StudentRepository studentRepository;
	
	/**
	 *@implNote this method is used to create student in DB
	 *return UUID
	 */
	@Override
	public Student saveStudent(StudentDTO studentDTO) throws Exception {
		
		return studentRepository.save(new Student(studentDTO.getStuAge(), studentDTO.getStuName(), Uuids.timeBased()));
	}

	/**
	 *@implNote this method is to findAll data present in DB
	 *@return List<Student>
	 */
	@Override
	public List<StudentDTO> allStudent()throws Exception {
	
		List<StudentDTO> studentList = new ArrayList<>();
		
		studentRepository.findAll().forEach(student -> {
			
			StudentDTO studentDTO = new StudentDTO();
			studentDTO.setStuId(student.getStuId().toString());
			studentDTO.setStuName(student.getStuName());
			studentDTO.setStuAge(student.getStuAge());
			
			studentList.add(studentDTO);
		});
		
		return studentList;

	}

	/**
	 * @implNote this method is to update student record
	 * @return boolean
	 */
	@Override
	public boolean updateStudent(String uuid, StudentDTO student) throws Exception {

		boolean status = false;

		Student studentData = studentById(UUID.fromString(uuid));
		
		if (studentData != null) {
			
			studentData.setStuName(student.getStuName());
			studentData.setStuAge(student.getStuAge());
			
			studentRepository.save(studentData);
			
			status = true;
			return status;

		} else {

			return status;
		}
	}
	
	/**
	 * @implNote this method is to delete student record
	 * @return boolean
	 */
	@Override
	public boolean deleteStudent(UUID studentId) throws Exception {

		boolean status = false;

		Student studentData = studentById(studentId);
		if (studentData != null) {
			studentRepository.delete(studentData);
			status = true;
			return status;

		} else {

			return status;
		}
	}

	/**
	 *@implNote this method is to get Student by id
	 *@return Student
	 */
	@Override
	public Student studentById(UUID studentId)throws Exception {
		
		Optional<Student> optionalStudent = studentRepository.findById(studentId);
		
		if(optionalStudent.isPresent()) {
			return optionalStudent.get();
		}else {
			return null;
		}
	}

	
}
