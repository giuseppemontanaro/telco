import { Role } from './role';

export interface User {
    username: string;
    email: string;
    password: string;
    isEmployee: boolean;
    token?: string;
}