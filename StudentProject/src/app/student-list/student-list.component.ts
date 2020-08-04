import { Component, OnInit } from '@angular/core';
import { StudentService } from '../student.service';
import { Student } from '../student';
import { Observable, Subject } from "rxjs";
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';


@Component({
  selector: 'app-student-list',
  templateUrl: './student-list.component.html',
  styleUrls: ['./student-list.component.scss']
})
export class StudentListComponent implements OnInit {

  constructor(private router: Router,private studentservice: StudentService) { }

  studentsArray: any[] = [];
  dtTrigger: Subject<any> = new Subject();

  students: Observable<Student[]>;
  student: Student = new Student();
  deleteMessage = false;
  studentlist: any;
  isupdated = false;

  ngOnInit() {

    this.isupdated = false;
    this.studentservice.getStudentList().subscribe(data => {
      this.students = data;
      this.dtTrigger.next();
    })
  }

  deleteStudent(id: String) {
    this.studentservice.deleteStudent(id)
      .subscribe(
        data => {
          console.log(data);
          this.deleteMessage = true;
          this.studentservice.getStudentList().subscribe(data => {
            this.students = data
          })
        },
        error => console.log(error));
  }

  updateStudent(id: String) {
    this.router.navigate(['/update-student/'+id]);
    // this.studentservice.getStudent(id)
    //   .subscribe(
    //     data => {
    //       this.studentlist = data
    //     },
    //     error => console.log(error));
  }

  studentupdateform = new FormGroup({
    student_id: new FormControl(),
    student_name: new FormControl(),
    student_email: new FormControl(),
    student_branch: new FormControl()
  });

  updateStu(updstu) {
    this.student = new Student();
    this.student.stuId = this.StudentId.value;
    this.student.stuName = this.StudentName.value;
    this.student.stuAge = this.StudentAge.value;


    this.studentservice.updateStudent(this.student.stuId, this.student).subscribe(
      data => {
        this.isupdated = true;
        this.studentservice.getStudentList().subscribe(data => {
          this.students = data
        })
      },
      error => console.log(error));
  }

  get StudentName() {
    return this.studentupdateform.get('student_name');
  }

  get StudentAge() {
    return this.studentupdateform.get('student_age');
  }

  get StudentId() {
    return this.studentupdateform.get('student_id');
  }

  changeisUpdate() {
    this.isupdated = false;
  }

}
