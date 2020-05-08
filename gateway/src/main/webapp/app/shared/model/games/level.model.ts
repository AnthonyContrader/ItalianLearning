export interface ILevel {
    id?: number;
    name?: string;
    description?: string;
    score?: number;
}

export class Level implements ILevel {
    constructor(public id?: number, public name?: string, public description?: string, public score?: number) {}
}
