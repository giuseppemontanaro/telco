import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { OptionalProduct } from 'src/app/models/optionalProduct';
import { Report } from 'src/app/models/report';
import { Service } from 'src/app/models/service';
import { ValidityPeriod } from 'src/app/models/validityPeriod';
import { OptionalProductDaoService } from 'src/app/services/optional-product-dao.service';
import { PackageDaoService } from 'src/app/services/package-dao.service';
import { SalesReportDaoService } from 'src/app/services/sales-report-dao.service';
import { Package } from '../../models/package';
import { DatePipe, formatDate } from '@angular/common';
import { Internet } from 'src/app/models/internet';
import { MobilePhone } from 'src/app/models/mobilePhone';
import { FixedPhone } from 'src/app/models/fixedPhone';

@Component({
  selector: 'app-employee-home',
  templateUrl: './employee-home.component.html',
  styleUrls: ['./employee-home.component.scss']
})
export class EmployeeHomeComponent implements OnInit {

  selectedOptionals: OptionalProduct[] = [];
  currentOptional!: OptionalProduct;
  optionals: OptionalProduct[] = [];
  optionalsLen!: number;

  selectedServices: Service[] = [];
  currentService!: string;
  services: string[] = ["Mobile Internet", "Mobile Phone", "Fixed Internet", "Fixed Phone"];
  servicesLen!: number;

  nameService!: string;
  months12Cost!: number;
  months24Cost!: number;
  months36Cost!: number;
  gigabytes!: number;
  gigabytesExtraFee!: number;
  sms!: number;
  smsExtraFee!: number;
  minutes!: number;
  minutesExtraFee!: number;

  nameOptional!: string;
  feeOptional!: number;

  report!: Report;
  columnsServicesTable: string[] = ['name', 'purchasestotal', 'purchases12months', 'purchases24months', 'purchases36months', 'totalSales', 'salesWithOptionals', 'avgOptionals'];
  columnsUsersTable: string[] = ['id', 'username', 'email'];
  columnsOrdersTable: string[] = ['creationDate', 'total', 'subscriptionDate', 'status', 'packageName'];
  columnsAlertTable: string[] = ['userId', 'username', 'email', 'amount', 'date'];
  bestSeller: string = '';

  datePipe = new DatePipe('en-US');

  constructor(private optionalDao: OptionalProductDaoService, 
              private packageDao: PackageDaoService,
              private salesReportDao: SalesReportDaoService,
              private snackBar: MatSnackBar) { }


  createPackageForm = new FormGroup({
    name: new FormControl('', [Validators.required]),
    optionals: new FormControl({ value: '', disabled: this.selectedOptionals.length === this.optionalsLen }),
    services: new FormControl({ value: '', disabled: this.selectedServices.length === this.servicesLen }),
    cost12: new FormControl('', [Validators.required]),
    cost24: new FormControl('', [Validators.required]),
    cost36: new FormControl('', [Validators.required])
  })

  createOptionalForm = new FormGroup({
    nameOptional: new FormControl('', [Validators.required]),
    feeOptional: new FormControl('', [Validators.required])
  })

  ngOnInit(): void {
    this.optionalDao.getOptionalProducts()
      .subscribe(optionals => {
        this.optionals = optionals;
        this.optionalsLen = optionals.length;
      });
    this.salesReportDao.getSalesReport()
    .subscribe(report => {
      this.report = report
      console.log(this.report);
    });
  }

  createServicePackage() {
    let validities: ValidityPeriod[] = [];
    validities.push(new ValidityPeriod(12, this.months12Cost));
    validities.push(new ValidityPeriod(24, this.months24Cost));
    validities.push(new ValidityPeriod(36, this.months36Cost));
    let toAdd: Package = { name: this.nameService, services: this.selectedServices, validityPeriods: validities, optionalProducts: this.selectedOptionals};
    console.log(toAdd);
    this.packageDao.addPackage(toAdd).subscribe();
    this.snackBar.open('Service package created');
    this.createPackageForm.reset();
  }

  setCurrentOptional(optional: OptionalProduct) {
    this.currentOptional = optional;
  }
  
  addOptional() {
    this.selectedOptionals.push(this.currentOptional);
    this.optionals = this.optionals.filter(elem => elem.name != this.currentOptional.name);
  }
  
  removeOptional(i: number): void {
    this.optionals.push(this.selectedOptionals[i]);
    this.selectedOptionals.splice(i, 1);
  }
  
  setSelectedServices(service: string) {
    this.currentService = service;
  }

  addService() {
    let service: Service;
    if (this.currentService == "Mobile Internet" || this.currentService == "Fixed Internet") {
      if (!this.gigabytes || !this.gigabytesExtraFee) {
        this.snackBar.open('Insert all the needed values to add the service: gigabytes and gigabytes extra fee', '', {duration: 5000});
        return;
      }
      let title: string;
      this.currentService == "Mobile Internet" ? title = "Mobile Internet" : title = "Fixed Internet";
      service = new Internet(title, this.gigabytes, this.gigabytesExtraFee);
    } else if (this.currentService == "Mobile Phone") {
      if (!this.sms || !this.smsExtraFee || !this.minutes || !this.minutesExtraFee) {
        this.snackBar.open('Insert all the needed values to add the service: sms, minutes and sms/minutes extra fee', '', {duration: 5000});
        return;
      }
      service = new MobilePhone("Mobile Phone", this.minutes, this.smsExtraFee, this.minutesExtraFee, this.smsExtraFee)
    } else {
      service = new FixedPhone("Fixed Phone");
    }
    this.selectedServices.push(service);
    this.services = this.services.filter(elem => elem != this.currentService);
  }
  
  removeService(i: number): void {
    this.services.push(this.selectedServices[i].title);
    this.selectedServices.splice(i, 1);
  }

  createOptional() {
    let optional: OptionalProduct = new OptionalProduct(this.nameOptional, this.feeOptional);
    this.optionalDao.addOptionalProducts(optional).subscribe();
    this.snackBar.open('Optional product created', '', {duration: 5000});
    this.createOptionalForm.reset();
  }
}
