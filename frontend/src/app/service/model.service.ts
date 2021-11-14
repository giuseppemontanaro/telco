import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ModelService {

  private beans = new Map<string, object>(); 

  constructor() { }

  getBean(key: string): object | undefined{
    return this.beans.get(key);
  }

  putBean(key: string, value: object) {
    this.beans.set(key, value)
  }

}
