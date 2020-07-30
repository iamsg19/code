import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { StudentListComponent } from './student-list/student-list.component';  
import { AddStudentComponent } from './add-student/add-student.component'; 
import { UpdateComponent } from './update/update.component';

const routes: Routes = [
  { path: "", redirectTo: 'view-student', pathMatch: 'full'},
  { path: 'view-student', component: StudentListComponent },
  { path: 'add-student', component: AddStudentComponent},
  { path: 'update-student/:id', component:UpdateComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
