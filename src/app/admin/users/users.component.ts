import { Component, OnInit, ViewChild } from '@angular/core';
import { UserService } from 'src/service/user.service';
import { AccountService } from 'src/service/account.service';

import { UserDTO } from 'src/dto/userdto';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html'
})
export class UsersComponent implements OnInit {

  usersDTO: UserDTO[];
  usertoinsert: UserDTO = new UserDTO();
  userview: UserDTO = new UserDTO();
  userType: string;
  @ViewChild('addUserForm') addUserForm;
  @ViewChild('newUserForm') userForm;
  @ViewChild('modalTitle') modalTitle;
  @ViewChild('closeModal') closeModal;
  @ViewChild('closeModalInsert') closeModalInsert;
  @ViewChild('activationButton') activationButton;

  constructor(private service: UserService, private serviceAccount: AccountService) { }

  ngOnInit() {
    this.getUsers();
  }

  getUsers() {
    this.service.getAll().subscribe(users => this.usersDTO = users);
  }

  delete(user: UserDTO) {
    this.service.deleteUser(user.login).subscribe(() => this.getUsers());
  }

  update(user: UserDTO, userType : string) {
    user.authorities = userType == "ROLE_ADMIN" ? ["ROLE_ADMIN", "ROLE_USER"] : ["ROLE_USER"];
    this.service.update(user).subscribe(() => this.getUsers());
    this.closeModal.nativeElement.click()
  }

  insert(user: UserDTO, userType: string) {
        
    this.serviceAccount.insert(user).subscribe(() => {
      this.service.readUser(user.login).subscribe(u => {
        u.authorities = userType == "ROLE_ADMIN" ? ["ROLE_ADMIN", "ROLE_USER"] : ["ROLE_USER"];
        this.service.update(u).subscribe(()=> this.getUsers());
      });
    });
    
    this.closeModalInsert.nativeElement.click()
  }

  clear(){
    this.usertoinsert = new UserDTO();
  }

  editUser(user: UserDTO){
    this.userForm.reset()
    if(user != null){
      this.service.readUser(user.login).subscribe(user => this.usertoinsert = user);
      this.modalTitle.nativeElement.textContent = 'Edit User ' + user.id;
    }
    else
      this.modalTitle.nativeElement.textContent = 'New User';
  }

  insertUser(){
    this.usertoinsert = new UserDTO();
    this.userType = null;
  }

  view(user : UserDTO){
    this.service.readUser(user.login).subscribe(user => this.userview = user);
    this.activationButton.nativeElement.textContent = user.activated ? 'Deactivate' : 'Activated';
  }

  activation(user : UserDTO){
    user.activated = !user.activated;
    this.service.update(user).subscribe(() => this.getUsers());
    this.activationButton.nativeElement.textContent = user.activated ? 'Deactivate' : 'Activated';
  }

  validation(){
    return (this.addUserForm.form.value.confermaPassword == this.addUserForm.form.value.password && this.addUserForm.form.valid) ? true : false;
  }

}
