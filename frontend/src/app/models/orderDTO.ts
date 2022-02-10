import { Order } from "./order";
import { User } from "./user";
import { ValidityPeriod } from "./validityPeriod";

export interface OrderDTO {
    purchase: Order;
    user: User;
    packageName: string;
    validityPeriod: ValidityPeriod;
}