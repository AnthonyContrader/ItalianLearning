import {LevelDTO} from './leveldto'
import {CategoryDTO} from './categorydto'

export class Hangman{

    typeGame: "Hangman";

    id: number;

    solution: string;
    
    definition: string;
    
    sentence: string;
    
    category: CategoryDTO;

    level: LevelDTO;
}