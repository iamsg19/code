import { Component, OnInit } from '@angular/core';
import { StudentService } from '../student.service';
import { Student } from '../student';
import { FormGroup, FormControl, Validators} from '@angular/forms';
import { Observable } from 'rxjs';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-update',
  templateUrl: './update.component.html',
  styleUrls: ['./update.component.scss']
})
export class UpdateComponent implements OnInit {

  student: Student;
  id: String;
  isupdated = false;
  studentlist: any;
  studentName: any;
  studentAge: any;
  studentId: any;

  constructor(private route: ActivatedRoute,private router: Router, private studentservice: StudentService) { }

  ngOnInit() {

    this.id = this.route.snapshot.params['id'];
    
    this.studentservice.getStudent(this.id)
      .subscribe(data => {
        this.studentlist = data;
        this.studentName = this.studentlist.stuName;
        this.studentAge = this.studentlist.stuAge;
        this.studentId = this.studentlist.stuId;
        console.log(this.studentName);
      
      }, error => console.log(error));
  }

  updateStudent(id: String){

      this.student = new Student();
      this.student.stuName = this.StudentName.value;
      this.student.stuAge = this.StudentAge.value;

      this.update(id);
    }

    update(id: String) {  
      this.studentservice.updateStudent(id, this.student)  
        .subscribe(data => console.log(data), error => console.log(error));  
        this.router.navigate(['/view-student']);
    } 

    studentupdateform= new FormGroup({
      student_name: new FormControl('',[Validators.required, Validators.minLength(5)]),
      student_age: new FormControl('',[Validators.required, Validators.maxLength(3)])
    });

    get StudentName(){  
      return this.studentupdateform.get('student_name');  
    }  
    
    get StudentAge(){  
      return this.studentupdateform.get('student_age');  
    }  
  }

