import { IFindAWord } from 'app/shared/model/games/find-a-word.model';
import { IFindMistake } from 'app/shared/model/games/find-mistake.model';
import { IGuessPicture } from 'app/shared/model/games/guess-picture.model';
import { IHangman } from 'app/shared/model/games/hangman.model';
import { IOrganizeSentence } from 'app/shared/model/games/organize-sentence.model';
import { IQuiz } from 'app/shared/model/games/quiz.model';

export interface ICategory {
    id?: number;
    title?: string;
    description?: string;
    findAWords?: IFindAWord[];
    findMistakes?: IFindMistake[];
    guessPictures?: IGuessPicture[];
    hangmen?: IHangman[];
    organizeSentences?: IOrganizeSentence[];
    quizzes?: IQuiz[];
}

export class Category implements ICategory {
    constructor(
        public id?: number,
        public title?: string,
        public description?: string,
        public findAWords?: IFindAWord[],
        public findMistakes?: IFindMistake[],
        public guessPictures?: IGuessPicture[],
        public hangmen?: IHangman[],
        public organizeSentences?: IOrganizeSentence[],
        public quizzes?: IQuiz[]
    ) {}
}
