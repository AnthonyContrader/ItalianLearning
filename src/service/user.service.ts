import { Injectable } from '@angular/core';
import { AbstractService } from './abstractservice';
import { UserDTO } from 'src/dto/userdto';
import { HttpClient } from '@angular/common/http';
import { LoginDTO } from 'src/dto/logindto';
import { Observable } from 'rxjs';

/**
 * I service sono decorati da @Injectable. 
 * Qui trovate, oltre ai metodi ereditati dall'Abstract,
 *  il metodo per il login (in mirror con il backend).
 * 
 * @author Vittorio Valent
 * 
 * @see AbstractService
 */
@Injectable({
  providedIn: 'root'
})
export class UserService extends AbstractService<UserDTO>{
  user: UserDTO;

  constructor(http: HttpClient) {
    super(http);
    this.type = 'users';
    this.micro = 'user';
  }

  auth() {
    this.user = JSON.parse(localStorage.getItem('currentUser'));
    console.log("stampa user: " + localStorage.getItem('currentUser'))
    if (this.user) {
      return 'Bearer ' + this.user.authorities;
    } else {
      return '';
    }
  }

  login(loginDTO: LoginDTO): Observable<UserDTO> {
    return this.http.post<any>('http://localhost:8080/api/authenticate', loginDTO)
  }

  getUserLogged(username: string) {
    return this.http.get('http://localhost:8080/api/users/' + username,
      {
        headers: { Authorization: this.auth() }
      });

  }
}