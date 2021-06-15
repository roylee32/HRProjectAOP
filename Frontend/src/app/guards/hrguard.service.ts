import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class HrguardService implements CanActivate{

  constructor(private router: Router) { }

  canActivate(): boolean{
    var role = localStorage.getItem("role");
    if (role == "User"){
      return true;
    } else {
      this.router.navigate(["pageNotAuthorized"]);
      return false;
    }
  }
}
