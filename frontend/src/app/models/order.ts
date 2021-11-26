import { UserStatus } from './userStatus';

export interface Order {
    date: Date;
    total: number;
    subscriptionDate: Date;
    status: UserStatus;
}