import { Service } from './service';
import { ValidityPeriod } from './validityPeriod';
import { OptionalProduct } from './optionalProduct';

export class ChosenPackage {
    
    name: string;
    services: Service[];
    validityPeriod: ValidityPeriod;
    products: OptionalProduct[];
    startDate: Date;
    orderId: number = -1;
    
    constructor(name: string, services: Service[], validityPeriod: ValidityPeriod, optionalProducts: OptionalProduct[], startDate: Date) {
        this.name = name;
        this.services = services;
        this.validityPeriod = validityPeriod;
        this.products = optionalProducts; 
        this.startDate = startDate;
    }

    public get totalCost(): number {
        return this.validityPeriod.monthly_fee * this.validityPeriod.month_number + 
        this.validityPeriod.month_number * this.products.map(elem => elem.monthly_fee).reduce((sum, elem) => sum + elem, 0)
    }

}