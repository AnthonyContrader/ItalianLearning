import { Injectable } from '@angular/core';
import { AbstractService } from './abstractservice';
import { OrganizeSentenceDTO } from 'src/dto/organizesentencedto';
import { HttpClient } from '@angular/common/http';
import { LoginDTO } from 'src/dto/logindto';
import { Observable } from 'rxjs';

/*
* created by Torquato Di Maio
*/

@Injectable({
    providedIn: 'root'
})
export class OrganizeSentenceService extends AbstractService<OrganizeSentenceDTO>{
    //HttpClient e' una classe che permette di effettuare chiamate Http
    constructor(http: HttpClient) {
      super(http);
      //indica il path quindi la view dove dobbiamo andare tramite il controller e quindi deve essere uguale a
      //quello tra parentesi tonde in @RequestMapping(/organizesentence) nel controller di OrganizeSentence in eclipse
      this.type = 'organize-sentences';
      this.micro = 'games';  
    }
  
  
}
  