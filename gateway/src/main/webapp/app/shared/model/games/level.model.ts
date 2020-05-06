import { IFindAWord } from 'app/shared/model/games/find-a-word.model';
import { IFindMistake } from 'app/shared/model/games/find-mistake.model';
import { IGuessPicture } from 'app/shared/model/games/guess-picture.model';
import { IHangman } from 'app/shared/model/games/hangman.model';
import { IOrganizeSentence } from 'app/shared/model/games/organize-sentence.model';
import { IQuiz } from 'app/shared/model/games/quiz.model';

export interface ILevel {
    id?: number;
    name?: string;
    description?: string;
    score?: number;
    findAWords?: IFindAWord[];
    findMistakes?: IFindMistake[];
    guessPictures?: IGuessPicture[];
    hangmen?: IHangman[];
    organizeSentences?: IOrganizeSentence[];
    quizzes?: IQuiz[];
}

export class Level implements ILevel {
    constructor(
        public id?: number,
        public name?: string,
        public description?: string,
        public score?: number,
        public findAWords?: IFindAWord[],
        public findMistakes?: IFindMistake[],
        public guessPictures?: IGuessPicture[],
        public hangmen?: IHangman[],
        public organizeSentences?: IOrganizeSentence[],
        public quizzes?: IQuiz[]
    ) {}
}
