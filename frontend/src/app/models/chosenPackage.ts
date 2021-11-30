import { Service } from './service';
import { ValidityPeriod } from './validityPeriod';
import { OptionalProduct } from './optionalProduct';

export class ChosenPackage {
    
    _name: string;
    _services: Service[];
    _validityPeriod: ValidityPeriod;
    _optionalProducts: OptionalProduct[];
    private _startDate: Date;

    public get startDate(): Date {
        return this._startDate;
    }
    
    public set startDate(value: Date) {
        this._startDate = value;
    }

    public get optionalProducts(): OptionalProduct[] {
        return this._optionalProducts;
    }
    
    public set optionalProducts(value: OptionalProduct[]) {
        this._optionalProducts = value;
    }

    public get validityPeriod(): ValidityPeriod {
        return this._validityPeriod;
    }
    
    public set validityPeriod(value: ValidityPeriod) {
        this._validityPeriod = value;
    }
    
    public get name(): string {
        return this._name;
    }

    public set name(value: string) {
        this._name = value;
    }

    public get services(): Service[] {
        return this._services;
    }
    public set services(value: Service[]) {
        this._services = value;
    }

    constructor(name: string, services: Service[], validityPeriod: ValidityPeriod, optionalProducts: OptionalProduct[], startDate: Date) {
        this._name = name;
        this._services = services;
        this._validityPeriod = validityPeriod;
        this._optionalProducts = optionalProducts; 
        this._startDate = startDate;
    }

    public get totalCost(): number {
        return this._validityPeriod.monthlyFee * this._validityPeriod.monthsNumber + 
        this._validityPeriod.monthsNumber * this._optionalProducts.map(elem => elem.monthlyFee).reduce((sum, elem) => sum + elem, 0)
    }
}