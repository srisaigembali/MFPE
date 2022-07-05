import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  user:User = new User('','');

  constructor(private service:AuthService, private router:Router) { }

  ngOnInit(): void {
  }

  register(){
    this.service.register(this.user).subscribe((res:any)=>{
      this.service.data=res;
      console.log(res);
      this.router.navigate(['login']);
    })
  }

}
