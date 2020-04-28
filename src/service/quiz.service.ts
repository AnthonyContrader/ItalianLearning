import { Injectable } from '@angular/core';
import { AbstractService } from './abstractservice';
import { HttpClient } from '@angular/common/http';
import {QuizDTO} from 'src/dto/quizdto';
/*created by Anna Cecere */
@Injectable({
    providedIn: 'root'
  })
  export class QuizService extends AbstractService<QuizDTO>{
  
    constructor(http: HttpClient) {
      super(http);
      this.type = 'quiz';
    }
  
  }