import { Order } from "./order";
import { Package } from "./package";
import { User } from "./user";
import { ValidityPeriod } from "./validityPeriod";

export interface OrderDTO {
    purchase: Order;
    user: User;
    chosenPackage: Package;
    validityPeriod: ValidityPeriod;
}