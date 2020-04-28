import {CategoryDTO} from "./categorydto";
import {LevelDTO} from "./leveldto";


export class FindAWord{

    typeGame = "FindAWord";
	
    id: Number;
    
    category: CategoryDTO;
    
    level: LevelDTO;
    
    solution: String ;
    
    definition: String;
    
    sentence: String ;

}


