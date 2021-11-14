import { Injectable } from '@angular/core';
import { HttpRequest, HttpHandler, HttpEvent, HttpInterceptor } from '@angular/common/http';
import { Observable } from 'rxjs';

import { environment } from 'src/environments/environment';
import { AuthenticationService } from '../services/authentication.service';
import { ModelService } from '../services/model.service';
import { Const } from '../shared/constants';
import { User } from '../models/user';

@Injectable()
export class JwtInterceptor implements HttpInterceptor {

  private user?: User;

  constructor(
    private authenticationService: AuthenticationService,
    private model: ModelService
  ) {
    this.model.getBean$(Const.USER).subscribe({
      next: user => this.user = user
    })
  }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const isLoggedIn = this.user && this.user.token;
    const isApiUrl = request.url.startsWith(environment.baseUrl);
    if (isLoggedIn && isApiUrl) {
      request = request.clone({
        setHeaders: {
          Authorization: `Bearer ${this.user?.token}`
        }
      });
    }

    return next.handle(request);
  }
}