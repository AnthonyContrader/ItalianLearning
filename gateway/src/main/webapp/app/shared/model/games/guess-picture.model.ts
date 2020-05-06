export interface IGuessPicture {
    id?: number;
    solution?: string;
    imageContentType?: string;
    image?: any;
    levelName?: string;
    levelId?: number;
    categoryTitle?: string;
    categoryId?: number;
}

export class GuessPicture implements IGuessPicture {
    constructor(
        public id?: number,
        public solution?: string,
        public imageContentType?: string,
        public image?: any,
        public levelName?: string,
        public levelId?: number,
        public categoryTitle?: string,
        public categoryId?: number
    ) {}
}
