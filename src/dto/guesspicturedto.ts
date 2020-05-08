import {CategoryDTO} from './categorydto';
import {LevelDTO} from './leveldto';

/*
 * @author Enzo Tasca
 */

export class GuessPictureDTO{
    typeGame: "GuessPicture";
	
    id: number;
    solution: string;
    image: string;
    
    categoryTitle: string;
    levelName: string;
    imageContentType: string;
	
	category: CategoryDTO;
    level: LevelDTO;
}