import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { HttpService } from 'src/app/services/http.service';

@Component({
  selector: 'app-employment-section',
  templateUrl: './employment-section.component.html',
  styleUrls: ['./employment-section.component.css']
})
export class EmploymentSectionComponent implements OnInit {

  @Input() visitedEid: number;

  editable = false;
  employmentForm = new FormGroup({
    workAuth:new FormControl(''),
    workAuthStartDate:new FormControl(''),
    workAuthEndDate: new FormControl(''),
    employmentStartDate: new FormControl(''),
    employmentEndDate: new FormControl(''),
    title: new FormControl(''),

  })
  constructor(private httpService: HttpService) { }

  ngOnInit(): void {
    this.getData();
    this.editable = false;
  }

  getData(): void {
     this.httpService.getData("/hr/api/userProfilePage/employmentInfo?eid="+(this.visitedEid == -1? localStorage.getItem("eid") : this.visitedEid.toString())).subscribe(
      (response) => {
         var JsonObject = JSON.parse(JSON.stringify(response));
         //if-else
         this.employmentForm.setValue({
           workAuth: JsonObject.workAuth,
           workAuthStartDate: JsonObject.workAuthStartDate,
           workAuthEndDate: JsonObject.workAuthEndDate,
           employmentStartDate: JsonObject.employmentStartDate,
           employmentEndDate: JsonObject.employmentEndDate,
           title: JsonObject.title
         });
       }
    );
  }



  makeEditable():void {
    this.editable = true;
  }

  cancel() {
    let conf = confirm("Are you sure to discard all your changes?");
    if (conf) {
      this.getData();
      this.editable = false;
    }
    else {
      return false;
    }
  }

  submitForm(form: FormGroup) {
    this.editable = false;
    var data =  {
      workAuth: form.value["workAuth"],
      workAuthStartDate: form.value["workAuthStartDate"],
      workAuthEndDate: form.value["workAuthEndDate"],
      employmentStartDate: form.value["employmentStartDate"],
      employmentEndDate: form.value["employmentEndDate"],
      title: form.value["title"],
      eid: (this.visitedEid == -1? localStorage.getItem("eid") : this.visitedEid.toString())
    };
    console.log(data)
    this.httpService.postData("/hr/api/userProfilePage/employmentInfo", data).subscribe(
      (data) => {
        var JsonObject = JSON.parse(JSON.stringify(data));
        if (JsonObject.errorMessage != null){
          //Set necessary form inputs as empty string.
        }
        else{
          location.reload;
        }
      }
    );
    
  }

}
