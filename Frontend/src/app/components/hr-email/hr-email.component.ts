import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { HttpService } from 'src/app/services/http.service';

@Component({
  selector: 'app-hr-email',
  templateUrl: './hr-email.component.html',
  styleUrls: ['./hr-email.component.css']
})
export class HrEmailComponent implements OnInit {

  errorMessage: string;
  hrEmailForm = new FormGroup({
    email: new FormControl('')
  });

  constructor(private httpService: HttpService, private router: Router) { }

  ngOnInit(): void {
  }

  submitForm(form: FormGroup){
    this.errorMessage = "Sending Email ..."
    var data = {
      email: form.value["email"]
    }
    this.httpService.postData("/hr/api/hrSendEmail", data).subscribe(
      (data) => {
        var JsonObject = JSON.parse(JSON.stringify(data));
        if (JsonObject.errorMessage != null){
          this.errorMessage = JsonObject.errorMessage;
          this.hrEmailForm.setValue({email:''});
        }
        else{
          console.log(localStorage.getItem("uid"));
          this.errorMessage = "Sent!";
          this.hrEmailForm.setValue({email:''});
        }
      }
    );
  }

}
