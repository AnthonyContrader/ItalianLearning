import { PlaylistDTO } from './playlistdto';

//Created By @Alessandro Alfieri
export class GamePlaylistDTO{

    id: number;

    idGame: number;

    typeGame: string;
    
    playlist: PlaylistDTO;

    playlistId: number;
    playlistName: string;
}