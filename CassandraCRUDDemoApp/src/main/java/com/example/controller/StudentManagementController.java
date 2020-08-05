package com.example.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Student;
import com.example.model.StudentDTO;
import com.example.service.StudentService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class StudentManagementController {

	@Autowired
	private StudentService studentService;
	
	/**
	 * @apiNote this api is for creating new record
	 * @param studentDTO
	 * @return
	 */
	@PostMapping("/create/student")
	public ResponseEntity<Student> createStudent(@RequestBody StudentDTO studentDTO) {

		try {

			Student student = studentService.saveStudent(studentDTO);
			return new ResponseEntity<>(student, HttpStatus.CREATED);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	/**
	 * @apiNote this api is for getting all the records
	 * @return List<StudentDTO>
	 */
	@GetMapping("/all/students")
	public ResponseEntity<List<StudentDTO>> allStudents(){
		
		try {
			
			List<StudentDTO> studentList = studentService.allStudent();
			if(!studentList.isEmpty()) {
				return new ResponseEntity<>(studentList, HttpStatus.OK);
			}else {
				return new ResponseEntity<>(studentList, HttpStatus.NO_CONTENT);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	/**
	 * @apiNote this api is for updating a record
	 * @return StudentDTO
	 */
	@PostMapping("/update/student/{id}")
	public ResponseEntity<?> updateStudent(@PathVariable("id") String uuid, @RequestBody StudentDTO studentDTO){
		
		try {
			
			boolean status = studentService.updateStudent(uuid, studentDTO);
			if(status) {
				return new ResponseEntity<>("Data updated successfully", HttpStatus.OK);
			}else {
				return new ResponseEntity<>("Record not found for updation", HttpStatus.NO_CONTENT);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	/**
	 * @apiNote this api is for delete a record
	 * @return StudentDTO
	 */
	@DeleteMapping("/delete/student")
	public ResponseEntity<?> deleteStudent(@RequestParam("uuid") String uuid){
		
		try {
			
			boolean status = studentService.deleteStudent(UUID.fromString(uuid));
			if(status) {
				return new ResponseEntity<>(studentService.allStudent(), HttpStatus.OK);
			}else {
				return new ResponseEntity<>("Record not found for deletion", HttpStatus.NO_CONTENT);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	/**
	 * @apiNote this api is for delete a record
	 * @return StudentDTO
	 */
	@GetMapping("/student/{id}")
	public ResponseEntity<?> studentById(@PathVariable("id") String uuid){
		
		try {
			
			Student student = studentService.studentById(UUID.fromString(uuid));
			if(student != null) {
				return new ResponseEntity<>(student, HttpStatus.OK);
			}else {
				return new ResponseEntity<>("No Records found for UUID : "+uuid, HttpStatus.ACCEPTED);
			}
			
		} catch(IllegalArgumentException iae) {
			
			iae.printStackTrace();
			return new ResponseEntity<>(iae.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
		catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
		}
	}
}
