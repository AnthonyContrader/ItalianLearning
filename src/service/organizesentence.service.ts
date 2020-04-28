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
  
    constructor(http: HttpClient) {
      super(http);
      this.type = 'organizesentence'; //indica il path quindi la view dove dobbiamo andare tramite il controller
    }
  
  
}
  