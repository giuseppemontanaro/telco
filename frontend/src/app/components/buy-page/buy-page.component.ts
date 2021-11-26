import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { PackageDaoService } from 'src/app/services/package-dao.service';
import { Package } from 'src/app/models/package';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { OptionalProduct } from 'src/app/models/optionalProduct';

@Component({
  selector: 'app-buy-page',
  templateUrl: './buy-page.component.html',
  styleUrls: ['./buy-page.component.scss']
})
export class BuyPageComponent implements OnInit {

  isServiceSelected: boolean = false;
  numOptProdsFields: number = 1;
  optProdsFields: number[] = [1];
  packages: Package[] = [];
  currentPackage: Package = {name: '', services: [], validityPeriods: [], optionalProducts: []};
  selectedOptionals: OptionalProduct[] = [];
  selectedOptional!: OptionalProduct;

  constructor(private router: Router, private packageDao: PackageDaoService) { }

  buyForm = new FormGroup({
    package: new FormControl('', [Validators.required]),
    validityPeriod: new FormControl({value: '', disabled: !this.isServiceSelected}, [Validators.required]),
    startDate: new FormControl({value: '', disabled: !this.isServiceSelected}, [Validators.required]),
    optional: new FormControl({value: '', disabled: !this.isServiceSelected})
  })

  ngOnInit(): void {
    this.packageDao.getPackages()
      .subscribe(packages => this.packages = packages);
  }

  setSelectedOptional(optional: OptionalProduct) {
    this.selectedOptional = optional;
  }

  addOptional(): void {
    this.selectedOptionals.push(this.selectedOptional);
    this.currentPackage.optionalProducts = this.currentPackage.optionalProducts.filter(elem => elem.name != this.selectedOptional.name);
  }

  removeOptional(index: number): void  {
    this.currentPackage.optionalProducts.push(this.selectedOptionals[index]);
    this.selectedOptionals.splice(index, 1);
  }

  goToConfirmation(): void {
    this.router.navigate(['confirmation']);
  }

  updateForm(currentPackage: string): void {
    this.isServiceSelected = true;
    this.buyForm.enable();
    this.packageDao.getPackageDetails(currentPackage)
      .subscribe(currentPackage => {
        this.currentPackage = currentPackage
      })
  }

  buy(): void {

  }

}
