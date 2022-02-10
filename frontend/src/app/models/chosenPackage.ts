import { Service } from './service';
import { ValidityPeriod } from './validityPeriod';
import { OptionalProduct } from './optionalProduct';

export class ChosenPackage {
    
    name: string;
    services: Service[];
    validityPeriod: ValidityPeriod;
    optionalProducts: OptionalProduct[];
    startDate: Date;
    
    constructor(name: string, services: Service[], validityPeriod: ValidityPeriod, optionalProducts: OptionalProduct[], startDate: Date) {
        this.name = name;
        this.services = services;
        this.validityPeriod = validityPeriod;
        this.optionalProducts = optionalProducts; 
        this.startDate = startDate;
    }

    public get totalCost(): number {
        return this.validityPeriod.monthly_fee * this.validityPeriod.month_number + 
        this.validityPeriod.month_number * this.optionalProducts.map(elem => elem.monthly_fee).reduce((sum, elem) => sum + elem, 0)
    }
}