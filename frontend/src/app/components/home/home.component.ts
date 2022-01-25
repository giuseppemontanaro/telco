import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { PackageDaoService } from 'src/app/services/package-dao.service';
import { Package } from 'src/app/models/package';
import { OrderDaoService } from 'src/app/services/order-dao.service';
import { Order } from 'src/app/models/order';
import { ModelService } from 'src/app/services/model.service';
import { Const } from 'src/app/shared/constants';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  packages: Package[] = [];
  rejected: Order[] = [];

  constructor(private router: Router, private packageDao: PackageDaoService, private orderDao: OrderDaoService, private model: ModelService) { }

  ngOnInit(): void {
    this.packageDao.getPackages()
      .subscribe(packages => this.packages = packages);
    const user = this.model.getBean(Const.USER);
    this.orderDao.getRejectedOrders(user)
      .subscribe(rejected => this.rejected = rejected);
    console.log(this.rejected);
  }

  goToBuyPage(): void {
    this.router.navigate(['buy-page'])
  }

  toConfirmation(i: number): void {
    this.model.putBean(Const.CHOSEN_PACKAGE, this.rejected[i].package);
    this.router.navigate(['confirmation'])
  }
}
