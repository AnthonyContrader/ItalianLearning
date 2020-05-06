//Created By @Alessandro Alfieri
import { Injectable } from '@angular/core';
import { AbstractService } from './abstractservice';
import { HangmanDTO } from 'src/dto/hangmandto';
import { HttpClient } from '@angular/common/http';

@Injectable({
    providedIn: 'root'
})
export class HangmanService extends AbstractService<HangmanDTO>{

    constructor(http: HttpClient){
        super(http);
        this.type = 'hangmen';
        this.micro = 'games';  
    }

}