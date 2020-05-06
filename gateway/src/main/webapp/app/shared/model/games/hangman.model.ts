export interface IHangman {
    id?: number;
    solution?: string;
    definition?: string;
    sentence?: string;
    categoryTitle?: string;
    categoryId?: number;
    levelName?: string;
    levelId?: number;
}

export class Hangman implements IHangman {
    constructor(
        public id?: number,
        public solution?: string,
        public definition?: string,
        public sentence?: string,
        public categoryTitle?: string,
        public categoryId?: number,
        public levelName?: string,
        public levelId?: number
    ) {}
}
