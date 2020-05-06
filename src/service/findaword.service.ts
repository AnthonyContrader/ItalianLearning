import { Injectable } from '@angular/core';
import { AbstractService } from './abstractservice';
import { FindAWordDTO } from 'src/dto/findaworddto';
import { HttpClient } from '@angular/common/http';

@Injectable({
    providedIn: 'root'
  })
  export class FindAWordService extends AbstractService<FindAWordDTO>{
  
    constructor(http: HttpClient) {
      super(http);
      this.type = 'find-a-words';
      this.micro= 'games';

    }
  
  }
