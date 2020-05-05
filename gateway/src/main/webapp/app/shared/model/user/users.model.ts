export const enum Usertype {
    ADMIN = 'ADMIN',
    USER = 'USER'
}

export interface IUsers {
    id?: number;
    username?: string;
    password?: string;
    usertype?: Usertype;
}

export class Users implements IUsers {
    constructor(public id?: number, public username?: string, public password?: string, public usertype?: Usertype) {}
}
