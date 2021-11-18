import { Component, OnInit } from '@angular/core';
import { ModelService } from 'src/app/services/model.service';
import { Const } from 'src/app/shared/constants';
import { User } from 'src/app/models/user';
import { ThrowStmt } from '@angular/compiler';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  tiles = [
  ];

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  goToBuyPage(): void {
    this.router.navigate(['buy-page'])
  }

}
