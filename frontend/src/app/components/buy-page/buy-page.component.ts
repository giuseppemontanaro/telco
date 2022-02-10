import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { PackageDaoService } from 'src/app/services/package-dao.service';
import { Package } from 'src/app/models/package';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { OptionalProduct } from 'src/app/models/optionalProduct';
import { ValidityPeriod } from 'src/app/models/validityPeriod';
import { ModelService } from 'src/app/services/model.service';
import { Const } from 'src/app/shared/constants';
import { ChosenPackage } from 'src/app/models/chosenPackage';

@Component({
  selector: 'app-buy-page',
  templateUrl: './buy-page.component.html',
  styleUrls: ['./buy-page.component.scss']
})
export class BuyPageComponent implements OnInit {

  isServiceSelected: boolean = false;
  optionalsLen!: number;
  chosenValidityPeriod!: ValidityPeriod;
  chosenDate!: Date;
  packages: Package[] = [];
  currentPackage: Package = {name: '', services: [], periods: [], products: []};
  selectedOptionals: OptionalProduct[] = [];
  selectedOptional!: OptionalProduct;

  constructor(private router: Router, private packageDao: PackageDaoService, private model: ModelService) { }

  buyForm = new FormGroup({
    package: new FormControl('', [Validators.required]),
    validityPeriod: new FormControl({value: '', disabled: !this.isServiceSelected}, [Validators.required]),
    startDate: new FormControl({value: '', disabled: !this.isServiceSelected}, [Validators.required]),
    optional: new FormControl({value: '', disabled: [!this.isServiceSelected, this.selectedOptionals.length == this.optionalsLen]})
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
    this.currentPackage.products = this.currentPackage.products.filter(elem => elem.name != this.selectedOptional.name);
  }

  removeOptional(index: number): void  {
    this.currentPackage.products.push(this.selectedOptionals[index]);
    this.selectedOptionals.splice(index, 1);
  }

  goToConfirmation(): void {
    let chosenPackage = new ChosenPackage(this.currentPackage.name, this.currentPackage.services, this.chosenValidityPeriod, this.selectedOptionals, this.chosenDate);
    this.model.putBean(Const.CHOSEN_PACKAGE, chosenPackage);
    this.router.navigate(['confirmation']);
  }

  updateForm(currentPackage: string): void {
    this.isServiceSelected = true;
    this.buyForm.enable();
    this.packageDao.getPackageDetails(currentPackage)
      .subscribe(currentPackage => {
        this.currentPackage = currentPackage;
        this.optionalsLen = currentPackage.products.length;
      })
  }

  getValidityPeriod(validity: ValidityPeriod): void {
    this.chosenValidityPeriod = validity;
  }

  getDate(date: Date) {
    this.chosenDate = date;
  }

}