import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { HttpService } from '../services/http.service';

@Injectable({
  providedIn: 'root'
})
export class ApplicationguardService implements CanActivate{

  pass:boolean;

  constructor(private httpService: HttpService, private router: Router) { }

  canActivate():boolean{
    var applicationStatus = localStorage.getItem("applicationStatus");
    console.log(applicationStatus);
    
    if (applicationStatus == "approved"){
      return true;
    } else {
      this.router.navigate(["pageNotAuthorized"]);
      return false;
    }

    // this.httpService.getData("/hr/api/getSimpleApplicationInfo/"+localStorage.getItem("email")).subscribe(
    //   (response) => {
    //     var JsonObject = JSON.parse(JSON.stringify(response));
    //     console.log(JsonObject);
    //     if (JsonObject.application == null){
    //       return false;
    //     } else {
    //       if (JsonObject.application.status == "approved"){
    //         return true;
    //       } else {
    //         return false;
    //       }
    //     }
    //   }
    // );
    // console.log(this.pass)
    // return this.pass;
  }
}
