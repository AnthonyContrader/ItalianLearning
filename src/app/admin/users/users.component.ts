import { Component, OnInit, ViewChild } from '@angular/core';
import { UserService } from 'src/service/user.service';
import { UserDTO } from 'src/dto/userdto';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {

  usersDTO: UserDTO[];
  usertoinsert: UserDTO = new UserDTO();
  userview: UserDTO = new UserDTO();
  @ViewChild('newUserForm') userForm;
  @ViewChild('modalTitle') modalTitle;
  @ViewChild('closeModal') closeModal;
  @ViewChild('activationButton') activationButton;


  constructor(private service: UserService) { }

  ngOnInit() {
    this.getUsers();
  }

  getUsers() {
    this.service.getAll().subscribe(users => this.usersDTO = users);
  }

  delete(user: UserDTO) {
    this.service.deleteUser(user.login).subscribe(() => this.getUsers());
  }

  update(user: UserDTO) {
    this.service.update(user).subscribe(() => this.getUsers());
  }

  insert(user: UserDTO) {
    this.service.insert(user).subscribe(() => this.getUsers());
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

  onSubmit(user: UserDTO) {
    console.log(user);
    /*
    if (user.id != null)
      this.update(user)
    else
      this.insert(user)
      
      this.closeModal.nativeElement.click()*/
  }
}
