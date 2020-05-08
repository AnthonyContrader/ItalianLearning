//Created By @Alessandro Alfieri
import {LevelDTO} from './leveldto'
import {CategoryDTO} from './categorydto'

export class FindMistakeDTO{

    typeGame: "FindMistake";

    id: number;

    solution: string;
    
    definition: string;

    optionA: string;
    
    optionB: string;
    
    optionC: string;

    sentence: string;
    
    category: CategoryDTO;

    level: LevelDTO;

    categoryTitle: string;
    levelName: string;
}