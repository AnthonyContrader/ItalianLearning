import {CategoryDTO} from './categoryDTO';
import {LevelDTO} from './levelDTO';
    

export class QuizDTO{
    typeGame: "Quiz";

    id:number;
    solution: string;
    definition: string;
    sentence: string;
    category: CategoryDTO; 
	level: LevelDTO; 
}