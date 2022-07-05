import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  data:any;
  authurl:string = "http://localhost:8084/auth/";

  constructor(private _httpClient:HttpClient, private router:Router) { }

  authenticate(user:any):any{
   return this._httpClient.post(this.authurl+"authenticate", user);
  }

  register(user:any):any{
    return this._httpClient.post(this.authurl+"register", user, {responseType:'text'});
  }

  isLoggedIn(){
    return localStorage.getItem('token')!=null;
  }

  getToken(){
    return localStorage.getItem('token')||'';
  }

  logout(){
    localStorage.clear();
    this.router.navigateByUrl('/login');
  }
}
