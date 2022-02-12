import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';
import { User } from '../models/user';
import { environment } from 'src/environments/environment';
import { handleError } from '../shared/handleError';

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
    return this.http.post<boolean>(`${this.baseUrl}/auth/login`, user, this.httpOptions)
      .pipe(
        catchError(
          handleError<boolean>('authUser', false)
        )
      );
  }

  signUpUser(user: User): Observable<boolean> {
    return this.http.post<boolean>(`${this.baseUrl}/auth/signUp`, user, this.httpOptions)
      .pipe(
        catchError(
          handleError<boolean>('signUpUser', false)
        )
      );
  }


}