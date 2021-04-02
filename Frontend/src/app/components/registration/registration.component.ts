import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { HttpService } from 'src/app/services/http.service';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  errorMessage: string;

  registrationForm = new FormGroup({
    username: new FormControl(""),
    password: new FormControl(""),
    passwordRetype: new FormControl(""),
    token: new FormControl("")
  });

  constructor(private httpService: HttpService) { }

  ngOnInit(): void {
  }

  submitForm(form: FormGroup){
    this.errorMessage="processing...";
    var requestBody = {
      username: form.value["username"],
      password: form.value["password"],
      passwordRetype: form.value["passwordRetype"],
      token: form.value["token"]
    }
    this.httpService.postData("/hr/api/registration", requestBody).subscribe(
      (response) => {
        var JsonObject = JSON.parse(JSON.stringify(response));
        if (JsonObject.errorMessage != null){
          this.errorMessage = JsonObject.errorMessage;
        } else {
          this.errorMessage = "New User Successfully Registered."
        }
      }
    );
  }

}
