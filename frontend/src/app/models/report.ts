import { Alert } from "./alert";
import { OptionalProduct } from "./optionalProduct";
import { Order } from "./order";
import { ServiceReport } from "./serviceReport";
import { User } from "./user";

export interface Report {
    
    servicesReports: ServiceReport[]; 
    insolventsUsers: User[]; 
    suspendedOrders: Order[]; 
    alerts: Alert[];
    bestSellerOptional: OptionalProduct;

}