import { AbstractService } from './abstractservice';
import { UserDTO } from 'src/dto/userdto';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
    providedIn: 'root'
  })export class AccountService extends AbstractService<UserDTO>{
  
    constructor(http: HttpClient) {
      super(http);
      this.type = 'accounts';
      this.micro = 'login';
    }
  
  }



