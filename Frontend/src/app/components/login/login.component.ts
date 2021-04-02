import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { HttpService } from 'src/app/services/http.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  errorMessage: string;

  loginForm = new FormGroup({
    usernameOrEmail: new FormControl(''),
    password: new FormControl('')
  });

  constructor(private httpService: HttpService, private router: Router, private cookieService: CookieService) { }

  ngOnInit(): void {}

  submitForm(form: FormGroup){
    this.errorMessage="processing...";
    var data = {
      usernameOrEmail: form.value["usernameOrEmail"],
      password: form.value["password"],
    }
    this.httpService.postData("/auth/api/login", data).subscribe(
      (response) => {
        var JsonObject = JSON.parse(JSON.stringify(response));
        if (JsonObject.errorMessage != null){
          this.errorMessage = JsonObject.errorMessage;
          this.loginForm.setValue({usernameOrEmail:"", password:""});
        } else {
          // this.cookieService.set("JWT", JsonObject.token);

          //console.log(JsonObject.uid);
          /*
          localStorage.setItem("uid", JsonObject.uid);
          this.router.navigateByUrl("home");
          */
          localStorage.setItem("JWT", JsonObject.token);

          localStorage.setItem("uid", JsonObject.uid);
          localStorage.setItem("email", JsonObject.email);
          localStorage.setItem("role", JsonObject.role);
          console.log(localStorage.getItem("uid"));

          this.router.navigateByUrl("onboarding");


          console.log(JsonObject.role == "HR");
          if (JsonObject.role == "HR"){
            this.router.navigate(["hrHomePage"]);
          } else {
            localStorage.removeItem("visitedEid");
            this.router.navigate(["onboarding"]);
          }
        }
      }
    );
}

}
