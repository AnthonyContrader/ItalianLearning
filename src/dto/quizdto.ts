import {CategoryDTO} from './categorydto';
import {LevelDTO} from './leveldto';
  
/*created by Anna Cecere */

export class QuizDTO{
    static typeGame: string = "Quiz";

    id:number;
    solution: string;
    definition: string;
    sentence: string;
    category: CategoryDTO; 
    level: LevelDTO; 
    categoryTitle: string;
    levelName: string;
}