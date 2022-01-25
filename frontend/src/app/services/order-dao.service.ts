import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError } from 'rxjs/operators';
import { handleError } from '../shared/handleError';
import { environment } from 'src/environments/environment';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class OrderDaoService {

  private baseUrl: string;
  
  private httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(private http: HttpClient) {
    this.baseUrl = environment.baseUrl;
  }

  createOrder(orderDTO: any) {
    console.log(`${this.baseUrl}/order/create`)
    console.log(orderDTO)
    return this.http.post<any>(`${this.baseUrl}/order/create`, orderDTO, this.httpOptions)
      .pipe(
        catchError(
          handleError<any>('authUser', undefined)
        )
      );
  }

  getRejectedOrders(user: User) {
    return this.http.post<any>(`${this.baseUrl}/order/rejected`, user, this.httpOptions)
      .pipe(
        catchError(
          handleError<any>('authUser', undefined)
        )
      );
  }
}
