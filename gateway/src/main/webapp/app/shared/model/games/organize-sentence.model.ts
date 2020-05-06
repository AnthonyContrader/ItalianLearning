export interface IOrganizeSentence {
    id?: number;
    solution?: string;
    definition?: string;
    sentence?: string;
    levelName?: string;
    levelId?: number;
    categoryTitle?: string;
    categoryId?: number;
}

export class OrganizeSentence implements IOrganizeSentence {
    constructor(
        public id?: number,
        public solution?: string,
        public definition?: string,
        public sentence?: string,
        public levelName?: string,
        public levelId?: number,
        public categoryTitle?: string,
        public categoryId?: number
    ) {}
}
