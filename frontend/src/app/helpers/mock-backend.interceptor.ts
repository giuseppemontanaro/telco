import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
  HttpResponse,
  HTTP_INTERCEPTORS
} from '@angular/common/http';
import { Observable, throwError, of } from 'rxjs';
import { delay, materialize, dematerialize } from 'rxjs/operators';
import { Role } from '../models/role';
import { ModelService } from '../services/model.service';
import { Const } from '../shared/constants';
import { User } from '../models/user';

@Injectable()
export class MockBackendInterceptor implements HttpInterceptor {

  constructor(private model: ModelService) {
    model.putBean(Const.MOCK_DB, { 
      users: 
      [
        { id: 1, username: 'user', password: 'password', email: 'user.@email.it', role: Role.User },
        { id: 2, username: 'employee', password: 'password', email: 'employee@etelco.it', role: Role.Emplyee }
      ]
    });
  }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const { url, method, headers, body } = request;

    switch (true) {
      case url.endsWith('/auth/login') && method === 'POST':
        return this.authenticate(body);
      case url.endsWith('/users') && method === 'POST':
        return this.signUpUser(body);
      default:
        return next.handle(request);
    }
  }

  private signUpUser(body: any): Observable<HttpEvent<any>> {
    let mockDb = this.model.getBean(Const.MOCK_DB);
    let newUser = body;
    newUser.role = Role.User;
    newUser.id = mockDb.users.length + 1;
    mockDb.users.push(newUser);
    this.model.putBean(Const.MOCK_DB, mockDb);
    return this.ok({})
  }

  private authenticate(body: any): Observable<HttpEvent<any>> {
    let users = this.model.getBean(Const.MOCK_DB).users;
    const { username, password } = body;
    const user = users.find((x: any) => (x.username === username || x.email === username) && x.password === password);
    if (!user) return this.error('Username or password is incorrect');
    return this.ok({
      id: user.id,
      username: user.username,
      email: user.email,
      password: user.password,
      role: user.role,
      token: `fake-jwt-token-${user.id}`
    });
  }

  private ok(body: any) {
    return of(new HttpResponse({ status: 200, body }))
      .pipe(delay(500)); // delay observable to simulate server api call
  }

  private error(message: any) {
    return throwError({ status: 400, error: { message } })
      .pipe(materialize(), delay(500), dematerialize());
  }

}

export const mockBackendProvider = {
  provide: HTTP_INTERCEPTORS,
  useClass: MockBackendInterceptor,
  multi: true
};