import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
  HttpResponse,
  HTTP_INTERCEPTORS,
  HttpParams
} from '@angular/common/http';
import { Observable, throwError, of } from 'rxjs';
import { delay, materialize, dematerialize } from 'rxjs/operators';
import { Role } from '../models/role';
import { ModelService } from '../services/model.service';
import { Const } from '../shared/constants';
import { Package } from '../models/package';

@Injectable()
export class MockBackendInterceptor implements HttpInterceptor {

  constructor(private model: ModelService) {
    model.putBean(Const.MOCK_DB, {
      users: [
        {
          id: 1, username: 'user', password: 'password', email: 'user.@email.it', role: Role.User,
          orders: {
            accepted: [],
            rejected: []
          }
        },
        {
          id: 2, username: 'employee', password: 'password', email: 'employee@etelco.it', role: Role.Emplyee,
          orders: {
            accepted: [],
            rejected: []
          }
        }
      ],
      packages: [
        {
          id: 1,
          name: "Basic",
          services: [
            { title: "Mobile Internet", gigabytes: 10, gigabytesExtraFee: 4 },
            { title: "Mobile Phone", minutes: 200, sms: 300, minutesExtraFee: 1, smsExtraFee: 0.5 }
          ],
          validityPeriods: [
            { monthsNumber: 12, monthlyFee: 10 },
            { monthsNumber: 24, monthlyFee: 17 },
            { monthsNumber: 36, monthlyFee: 22 }
          ],
          optionalProducts: [
            { name: "SMS news feed", monthlyFee: 1 },
            { name: "internet TV channel", monthlyFee: 3 },
          ]
        },
        {
          id: 2,
          name: "Family",
          services: [
            { title: "Mobile Internet", gigabytes: 10, gigabytesExtraFee: 4 },
            { title: "Fixed Phone" },
            { title: "Fixed Internet", gigabytes: 20, gigabytesExtraFee: 3 },
          ],
          validityPeriods: [
            { monthsNumber: 12, monthlyFee: 7 },
            { monthsNumber: 24, monthlyFee: 10 },
            { monthsNumber: 36, monthlyFee: 12 }
          ],
          optionalProducts: [
            { name: "SMS news feed", monthlyFee: 1 },
            { name: "internet TV channel", monthlyFee: 3 },
          ]
        }
      ]
    });
  }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const { url, method, headers, body, params } = request;

    console.log(request)
    switch (true) {
      case url.endsWith('/auth/login') && method === 'POST':
        return this.authenticate(body);
      case url.endsWith('/users') && method === 'POST':
        return this.signUpUser(body);
      case url.endsWith('/packages') && method === 'GET':
        return this.getPackages();
      case url.endsWith('/packages/details') && method === 'GET':
        return this.getPackageDetails(params);
      case url.endsWith('/packages/confirm') && method === 'POST':
        return this.getPackageDetails(params);
      case url.endsWith('/order/create') && method === 'POST':
        return this.createOrder(body);
      case url.endsWith('/order/rejected') && method === 'POST':
        return this.getRejectedOrders(body);
      default:
        return next.handle(request);
    }
  }

  private createOrder(body: any): Observable<HttpEvent<any>> {
    let mockDb = this.model.getBean(Const.MOCK_DB);
    let users = mockDb.users
    let user = users.find((x: any) => (x.username === body.username));
    // body.isRejected ? user.orders.rejected.push(body.order) : user.orders.accepted.push(body.order);
    // users.array.forEach((element: any) => {
    //   if (element.username === body.username) {
    //     element = user
    //   }
    // });
    mockDb.users = users;
    this.model.putBean(Const.MOCK_DB, mockDb);
    return this.ok({});
  }

  private getRejectedOrders(body: any): Observable<HttpEvent<any>> {
    let users = this.model.getBean(Const.MOCK_DB).users
    console.log(users)
    const user = users.find((x: any) => (x.username === body.username));
    return this.ok(user.orders.rejected);
  }

  private getPackageDetails(params: HttpParams): Observable<HttpEvent<any>> {
    let packages = this.model.getBean(Const.MOCK_DB).packages;
    const chosen = params.get("package") as string;
    return this.ok(packages.find((elem: Package) => elem.name = chosen))
  }

  private getPackages(): Observable<HttpEvent<any>> {
    let mockDb = this.model.getBean(Const.MOCK_DB);
    return this.ok(mockDb.packages)
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
      role: user.role,
      token: `fake-jwt-token-${user.id}`
    });
  }

  private ok(body: any) {
    return of(new HttpResponse({ status: 200, body }))
      .pipe(delay(500));
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