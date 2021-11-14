import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';
import { Const } from '../shared/constants';


@Injectable({
  providedIn: 'root'
})
export class ModelService {

  private beans: Map<string, any> = new Map<string, any>();
  private beans$: Map<string, Subject<any>> = new Map<string, Subject<any>>();

  constructor() {
  }

  getBean(key: Const): any {
    return this.beans.get(key.toString());
  }

  getTypedBean<T>(key: Const, type: new (...args: any[]) => T): T {
    return (this.beans.get(key.toString()) as unknown as T);
  }

  getTypedList<T>(key: Const, type: new (...args: any[]) => T): T[] {
    return (this.beans.get(key.toString()) as unknown as T[]);
  }

  private get$(key: Const): Subject<any> {
    let $ = this.beans$.get(key.toString());
    if (!$) {
      $ = new Subject();
      this.beans$.set(key.toString(), $);
    }
    return $;
  }

  private getWithString$(key: string): Subject<any> {
    let $ = this.beans$.get(key);
    if (!$) {
      $ = new Subject();
      this.beans$.set(key, $);
    }
    return $;
  }

  putBean(key: Const, bean: any): void {
    const $ = this.get$(key);
    this.beans.set(key.toString(), bean);
    $.next(bean);
  }

  putBeanWithString(key: string, bean: any) {
    const $ = this.getWithString$(key);
    this.beans.set(key.toString(), bean);
    $.next(bean);
  }

  removeBean(key: Const): void {
    const $ = this.get$(key);
    this.beans.delete(key.toString());
    $.next(undefined);
  }

  getBean$(key: Const) {
    const $ = this.get$(key);
    return $;
  }

  getBeanWithString$(key: string) {
    const $ = this.getWithString$(key);
    return $;
  }

  getTypedBean$<T>(key: Const, type: (...args: any[]) => T): Subject<T> {
    return this.get$(key) as unknown as Subject<T>;
  }
}