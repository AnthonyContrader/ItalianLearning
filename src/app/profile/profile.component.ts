import { Component, OnInit } from '@angular/core';
import { UserDTO } from 'src/dto/userdto';
import { AccountService } from 'src/service/accountservice.service';

@Component({
    selector: 'app-profile',
    templateUrl: './profile.component.html'
  })
  
  export class ProfileComponent implements OnInit {

    user: UserDTO = new UserDTO;

    constructor( private service : AccountService) {}
  
    ngOnInit() {
      this.user = JSON.parse(localStorage.getItem('currentUserData'));
      let auth = JSON.parse(localStorage.getItem('currentUser'));
      if (auth == null){
        window.location.href = '/login';
      }
    }
  
   
  }