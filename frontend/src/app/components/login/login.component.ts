import { Component, OnInit } from '@angular/core';
import { trigger, state, style, transition, animate } from '@angular/animations';
import { FormBuilder, FormControl, Validators } from '@angular/forms';
import { UserDaoService } from 'src/app/service/user-dao.service';
import { User } from 'src/app/model/user';
import { Router } from '@angular/router';
import { ModelService } from 'src/app/service/model.service';
import { USER } from 'src/app/model/constants';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
  animations: [
    trigger('toDisappered', [
      state('visible', style({
        opacity: 1
      })),
      state('disappered', style({
        opacity: 0
      })),
      transition('visible => disappered', [
        animate('1s')
      ]),
      transition('disappered => visible', [
        animate('1s')
      ]),
    ]),
    trigger('appearFromRight', [
      state('right', style({
        transform: 'translateX(70vw)'
      })),
      state('center', style({
        transform: 'translateX(0)' 
      })),
      transition('right => center', [
        animate('1s')
      ]),
      transition('center => right', [
        animate('1s')
      ]),
    ])
  ]
})
export class LoginComponent implements OnInit {

  loginForm = this.formBuilder.group({
    username: '',
    password: ''
  });

  signupForm = this.formBuilder.group({
    username: '',
    password: '',
    email: ''
  });

  email = new FormControl('', Validators.required);
  signUpPressed = false;

  constructor(
    private userDao: UserDaoService,
    private formBuilder: FormBuilder,
    private router: Router,
    private model: ModelService
  ) {}

  ngOnInit(): void {
  }

  goToSignUp(): void {
    this.signUpPressed = !this.signUpPressed;
  }

  login(): void {
    let user: User = this.loginForm.value;
    this.userDao.authUser(user)
      .subscribe((response) => {
        if (!!response) {
          this.model.putBean(USER, user);
          this.router.navigate(['home'])
        } else {
          console.log("nooop")
        }
      });
  }

  signup(): void {
    let user: User = this.signupForm.value;
    this.userDao.signUpUser(user)
      .subscribe((response) => {
        this.signUpPressed = false;
      });
  }
}
