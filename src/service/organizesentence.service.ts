import { Injectable } from '@angular/core';
import { AbstractService } from './abstractservice';
import { OrganizeSentenceDTO } from 'src/dto/organizesentencedto';
import { HttpClient } from '@angular/common/http';
import { LoginDTO } from 'src/dto/logindto';
import { Observable } from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class OrganizeSentenceService extends AbstractService<OrganizeSentenceDTO>{
  
    constructor(http: HttpClient) {
      super(http);
      this.type = 'user';
    }
  
  
}
  