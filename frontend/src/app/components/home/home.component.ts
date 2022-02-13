import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { PackageDaoService } from 'src/app/services/package-dao.service';
import { Package } from 'src/app/models/package';
import { OrderDaoService } from 'src/app/services/order-dao.service';
import { Order } from 'src/app/models/order';
import { ModelService } from 'src/app/services/model.service';
import { Const } from 'src/app/shared/constants';
import { Service } from 'src/app/models/service';
import { MobilePhone } from 'src/app/models/mobilePhone';
import { Internet } from 'src/app/models/internet';
import { ChosenPackage } from 'src/app/models/chosenPackage';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  packages: Package[] = [];
  rejected: any[] = [];
  isLoaded: boolean = false;

  constructor(private router: Router, private packageDao: PackageDaoService, private orderDao: OrderDaoService, private model: ModelService) { }

  ngOnInit(): void {
    const user = this.model.getBean(Const.USER);
    this.orderDao.getRejectedOrders(user)
      .subscribe(rejected => {
        this.rejected = rejected;
        this.isLoaded = true;
        console.log(this.rejected)
        this.packageDao.getPackages()
          .subscribe(packages => this.packages = packages);
      });
  }

  goToBuyPage(): void {
    this.router.navigate(['buy-page'])
  }

  toConfirmation(i: number): void {
    let pickedPackage = this.rejected[i].servicePackage;
    let chosenPackage = new ChosenPackage(pickedPackage.name, pickedPackage.services, this.rejected[i].validityPeriod, this.rejected[i].purchase.products, this.rejected[i].purchase.subscription_date);
    chosenPackage.orderId =  this.rejected[i].purchase.id;
    this.model.putBean(Const.CHOSEN_PACKAGE, chosenPackage);
    this.router.navigate(['confirmation'])
  }

  printDetails(service: Service) {
    if (service.title == 'Mobile Internet' || service.title == 'Fixed Internet') {
      return (service as Internet).gigabytes + ' GB - $' + (service as Internet).gigabytes_extra_fee + ' extra fee'
    } else if (service.title == 'Mobile Phone') {
      return (service as MobilePhone).sms + ' SMS - $' + (service as MobilePhone).sms_extra_fee + ' extra fee ' + (service as MobilePhone).minutes + ' minutes - $' + (service as MobilePhone).minutes_extra_fee + ' extra fee'
    }
    return ''
  }

  displayOrder(order: Order) {
    return order.date.toLocaleString().substring(0, 10) + ' - $' + order.total;
  }
}
