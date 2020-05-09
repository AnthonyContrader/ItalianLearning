import { Component, OnInit, ViewChild } from '@angular/core';
import { LoginDTO } from 'src/dto/logindto';
import { NgForm } from '@angular/forms';
import { UserService } from 'src/service/user.service';
import { Router } from '@angular/router';
import { UserDTO } from 'src/dto/userdto';
import { ConvertActionBindingResult } from '@angular/compiler/src/compiler_util/expression_converter';
import { AccountService } from 'src/service/accountservice.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginDTO: LoginDTO;
  logintoinsert: UserDTO = new UserDTO();
  userType: string;
  
  @ViewChild('newLoginForm') userForm;
  @ViewChild('modalTitle') modalTitle;
  @ViewChild('closeModal') closeModal;
  @ViewChild('activationButton') activationButton;


  constructor(private service: UserService, private router: Router, private serviceAccount: AccountService) { }

  ngOnInit() {
  }

  login(f: NgForm): void {
    this.loginDTO = new LoginDTO(f.value.username, f.value.password);
    this.service.login(this.loginDTO).subscribe((response: any) => {
      localStorage.setItem('currentUser', JSON.stringify({ authorities: response.id_token }));

      this.service.getUserLogged(this.loginDTO.username).subscribe((response: UserDTO) => {
        localStorage.setItem('currentUserData', JSON.stringify(response));

        if (response.authorities.includes('ROLE_ADMIN')) {
          this.router.navigate(['/admin-dashboard']);
        } else  if (response.authorities.includes('ROLE_USER')){
          this.router.navigate(['/user-dashboard']);
        }
      });
    });
  }
  editLogin(login: UserDTO){

    this.userForm.reset()
   /* localStorage.setItem('currentUser','admin')*/
    if(login != null){
      this.service.insert2(login).subscribe(login => this.logintoinsert = login);
      this.modalTitle.nativeElement.textContent = 'Edit Login ' + login.id
    }
    else 
      this.modalTitle.nativeElement.textContent = 'New Login'
     /* localStorage.removeItem('currentUser')*/
    }

  insertLogin(){
    this.logintoinsert = new UserDTO();
    this.userType = null;
    
  }
  activation(login : UserDTO){
    login.activated = !login.activated;
    this.service.update(login).subscribe(() => this.home());
    this.activationButton.nativeElement.textContent = login.activated ? 'Deactivate' : 'Activated';
 
  }
  home(){
    this.router.navigate(['/login']);
  }
 
 /*
  onSubmit(login: UserDTO) {
    console.log(login);
    */
}