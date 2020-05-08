import {CategoryDTO} from "./categorydto";
import {LevelDTO} from "./leveldto";


export class FindAWordDTO{

    typeGame : "FindAWord";
	
    id: number;
    
    category: CategoryDTO;
    
    level: LevelDTO;
    
    solution: string ;
    
    definition: string;
    
    sentence: string ;

    categoryTitle: string;
    levelName: string;

}


