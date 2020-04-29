/**
 * Classe DTO di User. DEVE essere uguale (stesso nome classe, stessi attributi e stessi nomi) a
 * quello nel backend. 
 * 
 * @see Usertype
 * 
 * @author Alessandro Alfieri
 */
import {LevelDTO} from './leveldto'
import {CategoryDTO} from './categorydto'

export class HangmanDTO{
    
    id: number;

    solution: string;
    
    definition: string;
    
    sentence: string;
    
    category: CategoryDTO;

    level: LevelDTO;
}