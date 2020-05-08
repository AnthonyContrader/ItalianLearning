export interface IGuessPicture {
    id?: number;
    solution?: string;
    imageContentType?: string;
    image?: any;
    categoryTitle?: string;
    categoryId?: number;
    levelName?: string;
    levelId?: number;
}

export class GuessPicture implements IGuessPicture {
    constructor(
        public id?: number,
        public solution?: string,
        public imageContentType?: string,
        public image?: any,
        public categoryTitle?: string,
        public categoryId?: number,
        public levelName?: string,
        public levelId?: number
    ) {}
}
