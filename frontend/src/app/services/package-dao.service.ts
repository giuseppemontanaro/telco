import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { catchError } from 'rxjs/operators';
import { handleError } from '../shared/handleError';
import { Package } from '../models/package';

@Injectable({
  providedIn: 'root'
})
export class PackageDaoService {
  
  private authUrl = 'auth/login';
  private signUpUrl = '/users';
  private baseUrl: string;

  constructor(private http: HttpClient) { 
    this.baseUrl = environment.baseUrl;
  }

  getPackages() {
    return this.http.get<Package[]>(`${this.baseUrl}/packages`)
      .pipe(
        catchError(
          handleError<Package[]>('authUser', [])
        )
      );
  }

  getPackageDetails(packageName: string) {
    return this.http.get<Package>(`${this.baseUrl}/packages/package`, {params: {packageName: packageName}})
      .pipe(
        catchError(
          handleError<Package>('authUser', undefined)
        )
      );
  }
  
  addPackage(packageToAdd: Package) {
    return this.http.post<any>(`${this.baseUrl}/packages/add`, packageToAdd)
      .pipe(
        catchError(
          handleError<any>('authUser', undefined)
        )
      );
  }

}
