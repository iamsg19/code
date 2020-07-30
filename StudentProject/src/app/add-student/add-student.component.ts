import { Component, OnInit } from '@angular/core';
import { Student } from '../student';
import { StudentService } from '../student.service';  
import {FormBuilder, FormControl,FormGroup,Validators} from '@angular/forms'; 

@Component({
  selector: 'app-add-student',
  templateUrl: './add-student.component.html',
  styleUrls: ['./add-student.component.scss']
})
export class AddStudentComponent implements OnInit {

  constructor(private studentservice:StudentService) { }
  student: Student;  
  submitted= false;
  ngOnInit() {
    this.submitted = false;
  }

  studentsaveform= new FormGroup({
    student_name: new FormControl('',[Validators.required, Validators.minLength(5)]),
    student_age: new FormControl('',[Validators.required, Validators.maxLength(3)])
  });



  saveStudent(saveStudent){
    this.student = new Student();
    this.student.stuName = this.StudentName.value;
    this.student.stuAge = this.StudentAge.value;
    this.submitted= true;
    this.save();
  }

  save() {  
    this.studentservice.createStudent(this.student)  
      .subscribe(data => console.log(data), error => console.log(error));  
    this.student = new Student();  
  }  

  get StudentName(){  
    return this.studentsaveform.get('student_name');  
  }  
  
  get StudentAge(){  
    return this.studentsaveform.get('student_age');  
  }  
  
  get StudentBranch(){  
    return this.studentsaveform.get('student_branch');  
  } 

  addStudentForm(){  
    this.submitted=false;  
    this.studentsaveform.reset();  
  }  
}
