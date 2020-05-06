export interface IFindMistake {
    id?: number;
    solution?: string;
    definition?: string;
    sentence?: string;
    optionA?: string;
    optionB?: string;
    optionC?: string;
    categoryTitle?: string;
    categoryId?: number;
    levelName?: string;
    levelId?: number;
}

export class FindMistake implements IFindMistake {
    constructor(
        public id?: number,
        public solution?: string,
        public definition?: string,
        public sentence?: string,
        public optionA?: string,
        public optionB?: string,
        public optionC?: string,
        public categoryTitle?: string,
        public categoryId?: number,
        public levelName?: string,
        public levelId?: number
    ) {}
}
