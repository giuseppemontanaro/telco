import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { Report } from '../models/report';
import { handleError } from '../shared/handleError';

@Injectable({
  providedIn: 'root'
})
export class SalesReportDaoService {

  private baseUrl: string;

  constructor(private http: HttpClient) {
    this.baseUrl = environment.baseUrl;
  }

  getSalesReport() {
    return this.http.get<Report>(`${this.baseUrl}/salesreport`)
      .pipe(
        catchError(
          handleError<Report>('authUser', undefined)
        )
      );
  }

}
