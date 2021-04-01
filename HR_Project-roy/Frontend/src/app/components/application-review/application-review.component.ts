import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { HttpService } from 'src/app/services/http.service';

@Component({
  selector: 'app-application-review',
  templateUrl: './application-review.component.html',
  styleUrls: ['./application-review.component.css']
})
export class ApplicationReviewComponent implements OnInit {

  applications;

  constructor(private httpService: HttpService) { }

  ngOnInit(): void {
    this.getApplicationInfo();
  }

  getApplicationInfo(){
    this.httpService.getData("/hr/api/getAllApplications").subscribe(
      (response) => {
        var JsonObject = JSON.parse(JSON.stringify(response));
        this.applications = JsonObject.applications;
        console.log(this.applications);
      }
    );
  }

}
