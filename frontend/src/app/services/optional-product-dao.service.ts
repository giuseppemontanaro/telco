import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { OptionalProduct } from '../models/optionalProduct';
import { handleError } from '../shared/handleError';

@Injectable({
  providedIn: 'root'
})
export class OptionalProductDaoService {

  private baseUrl: string;

  constructor(private http: HttpClient) {
    this.baseUrl = environment.baseUrl;
  }

  getOptionalProducts() {
    return this.http.get<any>(`${this.baseUrl}/optionalproducts`)
      .pipe(
        catchError(
          handleError<any>('authUser', undefined)
        )
      );
  }

  addOptionalProducts(optionalProduct: OptionalProduct) {
    return this.http.post<any>(`${this.baseUrl}/optionalproducts/add`, optionalProduct)
      .pipe(
        catchError(
          handleError<any>('authUser', undefined)
        )
      );
  }
}
