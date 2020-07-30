import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'; 
import { Observable } from 'rxjs';  

@Injectable({
  providedIn: 'root'
})
export class StudentService {

  private baseUrl = 'http://localhost:8089/api/';  

  constructor(private http: HttpClient) { }

  getStudentList(): Observable<any>{
    return this.http.get(`${this.baseUrl}`+'all/students');
  }

  deleteStudent(id: String): Observable<any> {  
    return this.http.delete(`${this.baseUrl}`+`delete/student?uuid=${id}`, { responseType: 'text' });  
  } 

  createStudent(student: object): Observable<object> {  
    return this.http.post(`${this.baseUrl}`+'create/student', student);  
  } 

  getStudent(id: String): Observable<Object> {  
    return this.http.get(`${this.baseUrl}`+`student/${id}`);  
  }  
  
  updateStudent(id: String, value: any): Observable<Object> {  
    return this.http.post(`${this.baseUrl}`+`/update/student/${id}`, value);  
  } 
}
