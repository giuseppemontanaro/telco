import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { HomeComponent } from './components/home/home.component';
import { AuthGuard } from './helpers/auth.guard';
import { Role } from './models/role';
import { BuyPageComponent } from './components/buy-page/buy-page.component';
import { ConfirmationComponent } from './components/confirmation/confirmation.component';
import { EmployeeHomeComponent } from './components/employee-home/employee-home.component';

const routes: Routes = [
  { 
    path: 'login', 
    component: LoginComponent 
  },
  { 
    path: 'home', 
    component: HomeComponent,
  },
  { 
    path: 'buy-page', 
    component: BuyPageComponent,
  },
  { 
    path: 'confirmation', 
    component: ConfirmationComponent,
  },
  {
     path: 'employee-home',
     component: EmployeeHomeComponent,
     canActivate: [AuthGuard],
   },
  { path: '**', redirectTo: 'login' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
