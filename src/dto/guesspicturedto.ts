import {CategoryDTO} from './categorydto';
import {LevelDTO} from './leveldto';

export class GuessPictureDTO{
    typeGame: "GuessPicture";
	
    id: number;
    solution: string;
	image: string;
	
	category: CategoryDTO;
    level: LevelDTO;
}