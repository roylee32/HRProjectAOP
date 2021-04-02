import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import jwt_decode from 'jwt-decode';

@Injectable({
  providedIn: 'root'
})
export class JWTGuardService implements CanActivate{

  constructor(private cookieService: CookieService, private router: Router) { }

  canActivate(): boolean {
    if (localStorage.getItem("JWT") == null){
      this.router.navigate(["login"]);
      return false;
    } else {
      var JWT = localStorage.getItem("JWT");
      try {
        var decoded = jwt_decode(JWT);
        return true;
      } catch (error) {
        this.router.navigate(["login"]);
      return false;
      }
    }
  }
}
