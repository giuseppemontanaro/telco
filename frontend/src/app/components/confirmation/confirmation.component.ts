import { Component, OnInit } from '@angular/core';
import { ModelService } from 'src/app/services/model.service';
import { Const } from 'src/app/shared/constants';
import { Router } from '@angular/router';
import { OrderDaoService } from 'src/app/services/order-dao.service';
import { ChosenPackage } from 'src/app/models/chosenPackage';
import { Order } from 'src/app/models/order';
import { UserStatus } from 'src/app/models/userStatus';
import { Package } from 'src/app/models/package';


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
      date: new Date(Date.now()),
      total: this.chosenPackage.totalCost,
      subscription_date: this.chosenPackage.startDate,
      isRejected: isRejected,
      package: this.chosenPackage
    }
    let selectedPackage:Package = {name: this.chosenPackage.name, 
      services: this.chosenPackage.services, 
      products: this.chosenPackage.products,
      periods : [this.chosenPackage.validityPeriod]}


    const user = this.model.getBean(Const.USER);
    this.orderDao.createOrder({purchase: order, user: user, chosenPackage: selectedPackage, validityPeriod: this.chosenPackage.validityPeriod}).subscribe();
    this.router.navigate(['/home']);
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
