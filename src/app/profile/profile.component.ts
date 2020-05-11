import { Component, OnInit } from '@angular/core';
import { UserDTO } from 'src/dto/userdto';
import { AccountService } from 'src/service/account.service';
import { ChangePasswordDTO } from 'src/dto/changepassworddto';

@Component({
    selector: 'app-profile',
    templateUrl: './profile.component.html'
  })
  
  export class ProfileComponent implements OnInit {

    user: UserDTO = new UserDTO;
    passwordtoinsert: ChangePasswordDTO = new ChangePasswordDTO;

    constructor( private service : AccountService) {}
  
    ngOnInit() {
      this.user = JSON.parse(localStorage.getItem('currentUserData'));
      let auth = JSON.parse(localStorage.getItem('currentUser'));
      if (auth == null){
        window.location.href = '/login';
      }
    }

    changePassword(newPassword : ChangePasswordDTO){
      this.service.changePassword(newPassword);
    }
  
   
  }