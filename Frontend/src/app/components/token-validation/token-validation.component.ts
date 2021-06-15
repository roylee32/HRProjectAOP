import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { HttpService } from 'src/app/services/http.service';

@Component({
  selector: 'app-token-validation',
  templateUrl: './token-validation.component.html',
  styleUrls: ['./token-validation.component.css']
})
export class TokenValidationComponent implements OnInit {

  errorMessage: String;

  validationForm = new FormGroup({
    token: new FormControl("")
  });

  constructor(private httpService: HttpService, private router: Router) { }

  ngOnInit(): void {
  }

  submitForm(form: FormGroup){
    this.errorMessage="processing..."
    var data = {
      token: form.value["token"]
    };
    this.httpService.postData("/hr/api/registrationTokenValidation", data).subscribe(
      (response) => {
        var JsonObject = JSON.parse(JSON.stringify(response));
        if (JsonObject.errorMessage != null){
          this.errorMessage = JsonObject.errorMessage;
          this.validationForm.setValue({token:""});
        } else {
          this.router.navigateByUrl("onboarding");
        }
      }
    );
  }

}
