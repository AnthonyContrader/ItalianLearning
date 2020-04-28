//Created By @Alessandro Alfieri
import { Injectable } from '@angular/core';
import { AbstractService } from './abstractservice';
import { PlaylistDTO } from 'src/dto/playlistdto';
import { HttpClient } from '@angular/common/http';

@Injectable({
    providedIn: 'root'
})
export class PlaylistService extends AbstractService<PlaylistDTO>{

    constructor(http: HttpClient){
        super(http);
        this.type = 'playlist';  
    }

}