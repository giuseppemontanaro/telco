import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { User } from '../models/user';
import { environment } from 'src/environments/environment';
import { ModelService } from './model.service';
import { Const } from '../shared/constants';


@Injectable({ providedIn: 'root' })
export class AuthenticationService {

  constructor(
    private router: Router,
    private http: HttpClient,
    private model: ModelService
  ) { }

  login(username: string, password: string) {
    return this.http.post<any>(`${environment.baseUrl}/auth/login`, { username, password })
      .pipe(
        map(user => {
          localStorage.setItem('user', JSON.stringify(user));
          this.model.putBean(Const.USER, user);
          return user;
        })
      );
  }

  logout() {
    localStorage.removeItem('user');
    this.model.putBean(Const.USER, null);
    this.router.navigate(['/login']);
  }
}