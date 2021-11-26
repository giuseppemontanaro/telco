import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { PackageDaoService } from 'src/app/services/package-dao.service';
import { Package } from 'src/app/models/package';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  packages: Package[] = [];

  constructor(private router: Router, private packageDao: PackageDaoService) { }

  ngOnInit(): void {
    this.packageDao.getPackages()
      .subscribe(packages => this.packages = packages)
    console.log(this.packages)
  }

  goToBuyPage(): void {
    this.router.navigate(['buy-page'])
  }

}
