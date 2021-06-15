import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { HttpService } from 'src/app/services/http.service';

@Component({
  selector: 'app-contact-info-section',
  templateUrl: './contact-info-section.component.html',
  styleUrls: ['./contact-info-section.component.css']
})
export class ContactInfoSectionComponent implements OnInit {

  @Input() visitedEid: number;

  editable = false;
  contactInfoForm = new FormGroup({
    personalEmail:new FormControl(''),
    cellPhone:new FormControl(''),
    workPhone: new FormControl('')
  })
  constructor(private httpService: HttpService) { }

  ngOnInit(): void {
    this.getData();
    this.editable = false;
  }

  getData(): void {
     this.httpService.getData("/hr/api/userProfilePage/contactInfo?eid="+(this.visitedEid == -1? localStorage.getItem("eid") : this.visitedEid.toString())).subscribe(
      (response) => {
         var JsonObject = JSON.parse(JSON.stringify(response));
         //if-else
         this.contactInfoForm.setValue({
           personalEmail: JsonObject.personalEmail,
           cellPhone: JsonObject.cellPhone,
           workPhone: JsonObject.workPhone
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
      personalEmail: form.value["personalEmail"],
      cellPhone: form.value["cellPhone"],
      workPhone: form.value["workPhone"],
      eid: (this.visitedEid == -1? localStorage.getItem("eid") : this.visitedEid.toString())
    };
    console.log(data)
    this.httpService.postData("/hr/api/userProfilePage/contactInfo", data).subscribe(
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
