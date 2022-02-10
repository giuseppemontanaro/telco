import { Service } from './service';
import { ValidityPeriod } from './validityPeriod';
import { OptionalProduct } from './optionalProduct';

export interface Package {
    name: string;
    services: Service[];
    periods: ValidityPeriod[];
    products: OptionalProduct[];
}