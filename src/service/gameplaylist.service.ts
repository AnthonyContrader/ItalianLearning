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

    findByPlaylist(id_playlist: number): Observable<any> {
        return this.http.get('http://localhost:' + this.port + '/' + this.type + '/getbyplaylist/' + id_playlist);
    }

    updatePlaylist(id_playlist: number, list: Array<Map<string,string>>): Observable<any>{
        return this.http.post('http://localhost:' + this.port + '/' + this.type + '/updateplaylist/' + id_playlist, list);
    }

    findGameInPlaylist(id_playlist: number, id_game: number, typeGame: string): Observable<any> {
        return this.http.get<boolean>('http://localhost:' + this.port + '/' + this.micro + '/api/' + this.type + '/findByGame?idGame= ' + id_game + '&idPlaylist=' + id_playlist + '&typeGame='+ typeGame, {
            headers: {
              Authorization: this.auth()
            }
        });
    }

}