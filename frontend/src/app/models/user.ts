import { Role } from './role';

export interface User {
    username: string;
    eMail: string;
    password: string;
    isEmployee: boolean;
    token?: string;
}