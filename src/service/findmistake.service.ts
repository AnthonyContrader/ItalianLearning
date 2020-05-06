//Created By @Alessandro Alfieri
import { Injectable } from '@angular/core';
import { AbstractService } from './abstractservice';
import { FindMistakeDTO } from 'src/dto/findmistakedto';
import { HttpClient } from '@angular/common/http';

@Injectable({
    providedIn: 'root'
})
export class FindMistakeService extends AbstractService<FindMistakeDTO>{

    constructor(http: HttpClient){
        super(http);
        this.type = 'findmistake';  
    }

}