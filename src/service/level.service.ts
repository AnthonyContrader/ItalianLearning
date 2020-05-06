//Created By @Alessandro Alfieri
import { Injectable } from '@angular/core';
import { AbstractService } from './abstractservice';
import { LevelDTO } from 'src/dto/leveldto';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class LevelService extends AbstractService<LevelDTO>{

    constructor(http: HttpClient){
        super(http);
        this.type = 'levels';  
        this.micro = 'games';
    }

}