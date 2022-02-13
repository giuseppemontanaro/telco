import { Role } from './role';

export interface User {
    id: number;
    username: string;
    eMail: string;
    password: string;
    isEmployee: boolean;
    token?: string;
}