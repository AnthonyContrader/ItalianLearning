export interface ICategory {
    id?: number;
    title?: string;
    description?: string;
}

export class Category implements ICategory {
    constructor(public id?: number, public title?: string, public description?: string) {}
}
