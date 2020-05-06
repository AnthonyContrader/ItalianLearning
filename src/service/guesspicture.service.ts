import { Injectable } from '@angular/core';
import { AbstractService } from './abstractservice';
import { GuessPictureDTO } from 'src/dto/guesspicturedto';
import { HttpClient } from '@angular/common/http';

/*
 * @author Enzo Tasca
 */

@Injectable({
    providedIn: 'root'
})
export class GuessPictureService extends AbstractService<GuessPictureDTO>{

    constructor(http: HttpClient){
        super(http);
        this.type = 'guesspicture';  
    }

}