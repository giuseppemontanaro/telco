import { Component, OnInit } from '@angular/core';
import { trigger, state, style, transition, animate } from '@angular/animations';
import { FormBuilder, FormGroup, Validators, FormControl } from '@angular/forms';
import { UserDaoService } from 'src/app/services/user-dao.service';
import { User } from 'src/app/models/user';
import { Router, RouterLink } from '@angular/router';
import { ModelService } from 'src/app/services/model.service';
import { Const } from 'src/app/shared/constants';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { Role } from '../../models/role';

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
    ]),
    trigger('rotate', [
      state('right-rotation', style({
        transform: 'rotate(5deg)'
      })),
      state('left-rotation', style({
        transform: 'rotate(-5deg)' 
      })),
      transition('right-rotation => left-rotation', [
        animate('1s')
      ]),
      transition('left-rotation => right-rotation', [
        animate('1s')
      ]),
    ])
  ]
})
export class LoginComponent implements OnInit {

  loginForm = new FormGroup({
    username: new FormControl('', [Validators.required]),
    password: new FormControl('', [Validators.required])
  })

  signupForm = this.formBuilder.group({
    username: new FormControl('', [Validators.required]),
    password: new FormControl('', [Validators.required]),
    email: new FormControl('', [Validators.required])
  });

  signUpPressed: boolean = false;
  isLandingFromSignup: boolean = false;
  isLandingFromLogin: boolean = false;

  constructor(
    private authService: AuthenticationService,
    private userDao: UserDaoService,
    private formBuilder: FormBuilder,
    private router: Router,
    private model: ModelService
  ) { }

  ngOnInit(): void {
    if (this.model.getBean(Const.LANDING_FROM_SIGNUP)) {
      this.signUpPressed = true;
      this.isLandingFromSignup = true;
      this.model.putBean(Const.LANDING_FROM_SIGNUP, undefined);
    }
    if (this.model.getBean(Const.LANDING_FROM_LOGIN)) {
      this.isLandingFromLogin = true;
      this.model.putBean(Const.LANDING_FROM_LOGIN, undefined);
    }
    
  }

  goToSignUp(): void {
    this.signUpPressed = !this.signUpPressed;
  }

  login(): void {
    let user: User = this.loginForm.value;
    this.authService.login(user.username, user.password)
      .subscribe((response) => {
        if (!!response) {
          this.model.putBean(Const.USER, response[0]);
          if (response[0].isEmployee) {
            this.router.navigate(['employee-home']);
            return;
          }
           this.isLandingFromLogin ? this.router.navigate(['confirmation']) : this.router.navigate(['home'])
        } else {
          console.log("user not found")
        }
      });

  }

  signup(): void {
    let user: User = this.signupForm.value;
    this.userDao.signUpUser(user)
      .subscribe((response) => {
        this.signUpPressed = false;
        if (this.isLandingFromLogin) this.router.navigate(['confirmation'])
      });
  }

}
