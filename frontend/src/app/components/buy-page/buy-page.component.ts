import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { PackageDaoService } from 'src/app/services/package-dao.service';
import { Package } from 'src/app/models/package';
import { FormGroup, FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-buy-page',
  templateUrl: './buy-page.component.html',
  styleUrls: ['./buy-page.component.scss']
})
export class BuyPageComponent implements OnInit {

  isServiceSelected: boolean = true;
  numOptProdsFields: number = 1;
  optProdsFields: number[] = [1];
  packages: Package[] = [];
  currentPackage: Package = {name: '', services: [], validityPeriods: [], optionalProducts: []};

  constructor(private router: Router, private packageDao: PackageDaoService) { }

  buyForm = new FormGroup({
    package: new FormControl('', [Validators.required]),
    validityPeriod: new FormControl('', [Validators.required]),
    startDate: new FormControl('', [Validators.required])
  })

  ngOnInit(): void {
    this.packageDao.getPackages()
      .subscribe(packages => this.packages = packages)
  }

  addOptional(): void {
    if (this.numOptProdsFields == 0) {
      this.numOptProdsFields = 1;
      return;
    }
    this.numOptProdsFields++;
    this.optProdsFields = Array(this.numOptProdsFields).fill(0).map((x, i) => i);
    console.log(this.numOptProdsFields);
    console.log(this.optProdsFields);
  }

  goToConfirmation(): void {
    this.router.navigate(['confirmation']);
  }

  updateForm(currentPackage: string): void {
    this.packageDao.getPackageDetails(currentPackage)
      .subscribe(currentPackage => this.currentPackage = currentPackage)
  }

  buy(): void {

  }

}
