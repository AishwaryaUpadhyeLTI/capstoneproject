import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, ValidationErrors, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { HttpService } from '../../services/http.service';
import { AuthService } from '../../services/auth.service';



@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  itemForm: FormGroup;
  formModel:any={};
  showError:boolean=false;
  errorMessage:any;

  constructor(public router:Router, public httpService:HttpService, private formBuilder: FormBuilder, private authService:AuthService) 
    {
      this.itemForm = this.formBuilder.group({
        username: ['',[ Validators.required]],
        password: ['',[ Validators.required]],
       
    });
  }

  ngOnInit(): void {

  }

  
  onSubmit(): void {

    if (this.itemForm.valid) {
      this.showError = false;
      this.httpService.Login(this.itemForm.value).subscribe((data: any) => {
        if (data.userNo != 0) {
         // debugger;
      
  
          //console.log(data.id);
          // alert(data.id);
          // alert(data.role);
          this.authService.SetId(data.id);
          // localStorage.setItem('role', data.role);
          this.authService.SetRole(data.role);
          this.authService.SetName(data.username);
         // this.authService.getUserId();
         // data.stateIdMd;
          this.authService.saveToken(data.token)
          this.router.navigateByUrl('/dashboard');
        
          
          setTimeout(() => {
            window.location.reload();
          }, 1000);
        } else {
          this.showError = true;
          this.errorMessage = "Wrong User or Password";
        }
      }, error => {
        // Handle error
        this.showError = true;
        this.errorMessage = "Bad Credentials! Please try again.";
        console.error('Login error:', error);
      });;
    } else {
      this.itemForm.markAllAsTouched();
    }
  }

  registration()
  {
    this.router.navigateByUrl('/registration');
  }
}