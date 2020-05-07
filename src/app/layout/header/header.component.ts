import { Component, OnInit } from '@angular/core';
import { UserDTO } from 'src/dto/userdto';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
})
export class HeaderComponent implements OnInit {

  user: UserDTO = new UserDTO;
  constructor() { }

  ngOnInit() {
    this.user = JSON.parse(localStorage.getItem('currentUser'));
    if (this.user == null){
      window.location.href = '/login';
    }
  }

  logout(){
    localStorage.removeItem('currentUser');
    window.location.href = '/login';
  }

}
