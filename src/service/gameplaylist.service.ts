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
        this.type = 'gameplaylist';  
    }

    findByPlaylist(id_playlist: number): Observable<any> {
        return this.http.get('http://localhost:' + this.port + '/' + this.type + '/getbyplaylist?id=' + id_playlist);
    }

    updatePlaylist(id_playlist: number, list: Array<Map<string,string>>){
        return this.http.post('http://localhost:' + this.port + '/' + this.type + '/updateplaylist?id=' + id_playlist, list);
    }

}