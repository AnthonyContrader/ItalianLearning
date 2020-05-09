import { Component, OnInit, ViewChild } from '@angular/core';
import { LoginDTO } from 'src/dto/logindto';
import { NgForm } from '@angular/forms';
import { UserService } from 'src/service/user.service';
import { Router } from '@angular/router';
import { UserDTO } from 'src/dto/userdto';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginDTO: LoginDTO;
  logintoinsert: UserDTO = new UserDTO();
  
  @ViewChild('newLoginForm') userForm;
  @ViewChild('modalTitle') modalTitle;
  @ViewChild('closeModal') closeModal;


  constructor(private service: UserService, private router: Router) { }

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
    if(login != null){
      this.service.readUser(login.login).subscribe(login => this.logintoinsert = login);
      this.modalTitle.nativeElement.textContent = 'Edit Login ' + login.id
    }
    else if(login.password == login.confermaPassword)
      this.modalTitle.nativeElement.textContent = 'New Login'
      
  }
   
 /*
  onSubmit(login: UserDTO) {
    console.log(login);
    */
}