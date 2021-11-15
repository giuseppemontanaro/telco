import { Component } from '@angular/core';
import { ModelService } from './services/model.service';
import { Const } from './shared/constants';
import { User } from './models/user';
import { UserDaoService } from './services/user-dao.service';
import { Role } from './models/role';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  
  title = 'Telco';
  user: User = {username: '', password: '', email: '', role: Role.User};

  constructor(private model: ModelService) {
    this.model.getBean$(Const.USER).subscribe(
      user => this.user = user
    )
  }
}
