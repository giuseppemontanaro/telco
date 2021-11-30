import { UserStatus } from './userStatus';
import { ChosenPackage } from './chosenPackage';

export interface Order {
    creationDate: Date;
    total: number;
    subscriptionDate: Date;
    status: UserStatus;
    package: ChosenPackage;
}