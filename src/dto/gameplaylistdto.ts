import { PlaylistDTO } from './playlistdto';

//Created By @Alessandro Alfieri
export class GamePlaylistDTO{

    id: number;

    id_game: number;

    type_game: string;
    
    playlist: PlaylistDTO;
}