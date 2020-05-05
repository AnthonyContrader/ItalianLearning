export const enum Usertype {
    ADMIN = 'ADMIN',
    USER = 'USER'
}

export interface IUser {
    id?: number;
    username?: string;
    password?: string;
    usertype?: Usertype;
}

export class User implements IUser {
    constructor(public id?: number, public username?: string, public password?: string, public usertype?: Usertype) {}
}
