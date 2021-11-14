import { Component, OnInit } from '@angular/core';
import { ModelService } from 'src/app/service/model.service';
import { USER } from 'src/app/model/constants';
import { User } from 'src/app/model/user';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  user: User;

  constructor(private model: ModelService) {
    this.user = model.getBean(USER) as User;
  }

  ngOnInit(): void {
  }

}
