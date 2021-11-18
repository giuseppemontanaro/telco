import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { HomeComponent } from './components/home/home.component';
import { AuthGuard } from './helpers/auth.guard';
import { Role } from './models/role';
import { BuyPageComponent } from './components/buy-page/buy-page.component';
import { ConfirmationComponent } from './components/confirmation/confirmation.component';

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
  // {
  //   path: 'employee',
  //   component: AdminComponent,
  //   canActivate: [AuthGuard],
  //   data: { roles: [Role.Emplyee] }
  // },
  { path: '**', redirectTo: 'home' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
