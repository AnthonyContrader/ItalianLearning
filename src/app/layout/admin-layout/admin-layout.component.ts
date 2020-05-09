import { Component, OnInit } from '@angular/core';
import { UserDTO } from 'src/dto/userdto';

@Component({
  selector: 'app-admin-layout',
  templateUrl: './admin-layout.component.html'
})
export class AdminLayoutComponent implements OnInit {
  user: UserDTO = new UserDTO;
  constructor() { }

  ngOnInit() {
    this.user = JSON.parse(localStorage.getItem('currentUserData'));
    let auth = JSON.parse(localStorage.getItem('currentUser'));
    if (auth == null){
      window.location.href = '/login';
    }
  }

  logout(){
    localStorage.removeItem('currentUser');
    window.location.href = '/login';
  }

}
