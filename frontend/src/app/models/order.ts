import { ChosenPackage } from './chosenPackage';

export interface Order {
    date: Date;
    total: number;
    subscription_date: Date;
    package: ChosenPackage;
    isRejected: boolean
}