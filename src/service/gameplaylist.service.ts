//Created By @Alessandro Alfieri
import { Injectable } from '@angular/core';
import { AbstractService } from './abstractservice';
import { GamePlaylistDTO } from 'src/dto/gameplaylistdto';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class GamePlaylistService extends AbstractService<GamePlaylistDTO>{

    constructor(http: HttpClient){
        super(http);
        this.type = 'game-playlists'; 
        this.micro = 'games';
    }

    deleteByPlaylist(id_playlist: number): Observable<any>{
        return this.http.delete('http://localhost:' + this.port + '/' + this.micro + '/api/' + this.type + '/deleteAllByPlaylist/' + id_playlist, {
            headers: {
              Authorization: this.auth()
            }
        });
    }

    findGameInPlaylist(id_playlist: number, id_game: number, typeGame: string): Observable<any> {
        return this.http.get<boolean>('http://localhost:' + this.port + '/' + this.micro + '/api/' + this.type + '/findByGame?idGame= ' + id_game + '&idPlaylist=' + id_playlist + '&typeGame='+ typeGame, {
            headers: {
              Authorization: this.auth()
            }
        });
    }

}