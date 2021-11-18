import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-buy-page',
  templateUrl: './buy-page.component.html',
  styleUrls: ['./buy-page.component.scss']
})
export class BuyPageComponent implements OnInit {

  isServiceSelected: boolean = true;
  optionals: string[] = [];
  numOptProdsFields: number = 1;
  optProdsFields: number[] = [1];

  constructor(private router: Router) { }

  ngOnInit(): void {
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

}
