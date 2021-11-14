import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';
import { User } from '../models/user';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UserDaoService {

  private authUrl = 'auth/login';
  private signUpUrl = '/users';
  private baseUrl: string;

  private httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(private http: HttpClient) {
    this.baseUrl = environment.baseUrl
  }

  authUser(user: User): Observable<boolean> {
    if (environment.mocked) {
      this.http.post<boolean>(`${this.baseUrl}/auth/login`, user, this.httpOptions)
        .pipe(
          catchError(
            this.handleError<boolean>('authUser', false)
          )
        );
    } else {
      
    }
    return this.http.post<boolean>(`${this.baseUrl}/auth/login`, user, this.httpOptions)
      .pipe(
        catchError(
          this.handleError<boolean>('authUser', false)
        )
      );
  }

  signUpUser(user: User): Observable<boolean> {
    return this.http.post<boolean>(`${this.baseUrl}/users`, user, this.httpOptions)
      .pipe(
        catchError(
          this.handleError<boolean>('signUpUser', false)
        )
      );
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error);
      return of(result as T);
    };
  }

}