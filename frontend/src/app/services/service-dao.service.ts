import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { handleError } from '../shared/handleError';

@Injectable({
  providedIn: 'root'
})
export class ServiceDaoService {

  private baseUrl: string;

  constructor(private http: HttpClient) {
    this.baseUrl = environment.baseUrl;
  }

  getServices() {
    return this.http.get<any>(`${this.baseUrl}/services`)
      .pipe(
        catchError(
          handleError<any>('authUser', undefined)
        )
      );
  }}
