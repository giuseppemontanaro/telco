import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';
import { User } from '../model/user';

@Injectable({
  providedIn: 'root'
})
export class UserDaoService {

  private authUrl = 'http://localhost:3000/auth/login';
  private signUpUrl = 'http://localhost:3000/users';

  private httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(private http: HttpClient) {}

  authUser(user: User): Observable<boolean> {
    return this.http.post<boolean>(this.authUrl, user, this.httpOptions)
      .pipe(
        catchError(
          this.handleError<boolean>('authUser', false)
        )
      );
  }

  signUpUser(user: User): Observable<boolean> {
    return this.http.post<boolean>(this.signUpUrl, user, this.httpOptions)
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