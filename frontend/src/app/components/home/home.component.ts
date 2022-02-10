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

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  packages: Package[] = [];
  rejected: Order[] = [];
  isLoaded: boolean = false;

  constructor(private router: Router, private packageDao: PackageDaoService, private orderDao: OrderDaoService, private model: ModelService) { }

  ngOnInit(): void {
    const user = this.model.getBean(Const.USER);
    this.orderDao.getRejectedOrders(user)
      .subscribe(rejected => {
        this.rejected = rejected;
        console.log(this.rejected);
        this.isLoaded = true;
      });
    this.packageDao.getPackages()
      .subscribe(packages => this.packages = packages);
  }

  goToBuyPage(): void {
    this.router.navigate(['buy-page'])
  }

  toConfirmation(i: number): void {
    this.model.putBean(Const.CHOSEN_PACKAGE, this.rejected[i].package);
    this.router.navigate(['confirmation'])
  }

  printDetails(service: Service) {
    if (service.title == 'Mobile Internet' || service.title == 'Fixed Internet') {
      return (service as Internet).gigabytes + ' GB - $' + (service as Internet).gigabytesExtraFee + ' extra fee'
    } else if (service.title == 'Mobile Phone') {
      return (service as MobilePhone).sms + ' SMS - $' + (service as MobilePhone).smsExtraFee + ' extra fee ' + (service as MobilePhone).minutes + ' minutes - $' + (service as MobilePhone).smsExtraFee + ' extra fee'
    }
    return ''
  }
}
