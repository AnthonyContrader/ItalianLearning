import { Component, OnInit, ViewChild } from '@angular/core';
import { LoginDTO } from 'src/dto/logindto';
import { NgForm } from '@angular/forms';
import { UserService } from 'src/service/user.service';
import { Router } from '@angular/router';
import { UserDTO } from 'src/dto/userdto';
import { AccountService } from 'src/service/account.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html'
})

export class LoginComponent implements OnInit {

  loginDTO: LoginDTO;
  usertoinsert: UserDTO = new UserDTO();
  userType: string;
  
  @ViewChild('registerForm') registerForm;
  @ViewChild('closeModal') closeModal;
  @ViewChild('activationButton') activationButton;


  constructor(private router: Router, private serviceAccount: AccountService, private serviceUser: UserService) { }

  ngOnInit() {
  }

  login(f: NgForm): void {
    this.loginDTO = new LoginDTO(f.value.username, f.value.password);
    this.serviceUser.login(this.loginDTO).subscribe((response: any) => {
      localStorage.setItem('currentUser', JSON.stringify({ authorities: response.id_token }));

      this.serviceUser.getUserLogged(this.loginDTO.username).subscribe((response: UserDTO) => {
        localStorage.setItem('currentUserData', JSON.stringify(response));

        if (response.authorities.includes('ROLE_ADMIN')) {
          this.router.navigate(['/admin-dashboard']);
        } else  if (response.authorities.includes('ROLE_USER')){
          this.router.navigate(['/user-dashboard']);
        }
      });
    });
  }

  register(u: UserDTO){
    this.serviceAccount.insert(u).subscribe();
    this.closeModal.nativeElement.click()
  }

  home(){
    this.router.navigate(['/login']);
  }

  validation(){
    return (this.registerForm.form.value.confermaPassword == this.registerForm.form.value.password && this.registerForm.form.valid) ? true : false;
  }
 
}