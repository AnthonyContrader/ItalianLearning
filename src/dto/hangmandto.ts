//Created By @Alessandro Alfieri
import {LevelDTO} from './leveldto'
import {CategoryDTO} from './categorydto'

export class HangmanDTO{
    
    typeGame: "Hangman";
    
    id: number;

    solution: string;
    
    definition: string;
    
    sentence: string;
    
    category: CategoryDTO;

    level: LevelDTO;

    categoryTitle: string;
    levelName: string;
}