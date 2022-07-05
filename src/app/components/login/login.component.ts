import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  user:User = new User('','');

  constructor(private service:AuthService, private router:Router) { }

  ngOnInit(): void {
  }

  login(){
    this.service.authenticate(this.user).subscribe((res:any)=>{
      this.service.data=res;
      console.log(res);
      localStorage.setItem('token', res.token);
      this.router.navigate(['']);
    })
  }

}
