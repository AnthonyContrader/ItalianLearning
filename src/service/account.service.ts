import { AbstractService } from './abstractservice';
import { UserDTO } from 'src/dto/userdto';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ChangePasswordDTO } from 'src/dto/changepassworddto';

@Injectable({
    providedIn: 'root'
  })export class AccountService extends AbstractService<UserDTO>{
  
    constructor(http: HttpClient) {
      super(http);
      this.type = 'accounts';
      this.micro = '';
    }

    insert(dto: UserDTO): Observable<any> {
      return this.http.post('http://localhost:' + this.port + '/api/register', dto);
    }

    changePassword(dto :ChangePasswordDTO): Observable<any> {
      return this.http.post('http://localhost:' + this.port + '/' + this.type + '/change-password', dto);
    }
  }
