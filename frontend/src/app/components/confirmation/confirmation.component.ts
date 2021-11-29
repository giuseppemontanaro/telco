import { Component, OnInit } from '@angular/core';
import { ModelService } from 'src/app/services/model.service';
import { Package } from 'src/app/models/package';
import { Const } from 'src/app/shared/constants';
import { Router } from '@angular/router';

@Component({
  selector: 'app-confirmation',
  templateUrl: './confirmation.component.html',
  styleUrls: ['./confirmation.component.scss']
})
export class ConfirmationComponent implements OnInit {

  chosenPackage!: Package;
  isLoggedIn: boolean = false;

  constructor(private model: ModelService, private router: Router) { }

  ngOnInit(): void {
    this.chosenPackage = this.model.getBean(Const.CHOSEN_PACKAGE);
    this.isLoggedIn = !!this.model.getBean(Const.USER);
  }

  buy() {

  }

  toLogin() {
    this.model.putBean(Const.LANDING_FROM_LOGIN, true);
    this.router.navigate(['/auth/login']);
  }

  toSignup() {
    this.model.putBean(Const.LANDING_FROM_SIGNUP, true);
    this.router.navigate(['/auth/login']);
  }
}
