import { Component, OnInit } from '@angular/core';
import { ModelService } from 'src/app/services/model.service';
import { Const } from 'src/app/shared/constants';
import { Router } from '@angular/router';
import { OrderDaoService } from 'src/app/services/order-dao.service';
import { ChosenPackage } from 'src/app/models/chosenPackage';
import { Order } from 'src/app/models/order';
import { UserStatus } from 'src/app/models/userStatus';

@Component({
  selector: 'app-confirmation',
  templateUrl: './confirmation.component.html',
  styleUrls: ['./confirmation.component.scss']
})
export class ConfirmationComponent implements OnInit {

  chosenPackage!: ChosenPackage;
  isLoggedIn: boolean = false;

  constructor(private model: ModelService, private router: Router, private orderDao: OrderDaoService) { }

  ngOnInit(): void {
    this.chosenPackage = this.model.getBean(Const.CHOSEN_PACKAGE);
    this.isLoggedIn = !!this.model.getBean(Const.USER);
  }

  buy(isRejected: boolean) {
    const order: Order = {
      creationDate: new Date(Date.now()),
      total: this.chosenPackage.totalCost,
      subscriptionDate: this.chosenPackage.startDate,
      status: UserStatus.Disposed,
      package: this.chosenPackage
    }
    const user = this.model.getBean(Const.USER);
    this.orderDao.createOrder({order: order, isRejected: isRejected, user: user});
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
