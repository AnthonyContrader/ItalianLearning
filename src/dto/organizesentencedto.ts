
import {CategoryDTO} from './categorydto';
import {LevelDTO} from './leveldto';

/*
* created by Torquato Di Maio
*/

export class OrganizeSentenceDTO{
    static typeGame: string = "OrganizeSentence";

    id: number; //tipo numerico
	
    solution: string; //tipo stringa 
    
    sentence: string; //frase disordinata
	
    definition: string;
	
	category: CategoryDTO;
	
    level: LevelDTO;
    
    categoryTitle: string;
    levelName: string;
    
}