import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { HttpService } from 'src/app/services/http.service';

@Component({
  selector: 'app-emergency-contact-section',
  templateUrl: './emergency-contact-section.component.html',
  styleUrls: ['./emergency-contact-section.component.css']
})
export class EmergencyContactSectionComponent implements OnInit {

  @Input() visitedEid: number;

  contactList= new Array();
  editable = false;
  /*
  emergencyContactForm = new FormGroup({
    firstName:new FormControl(''),
    phone:new FormControl(''),
    address: new FormControl('')
  })
  */
  constructor(private httpService: HttpService) { }

  ngOnInit(): void {
    this.getData();
    this.editable = false;
  }

  getData(): void {
    this.httpService.getData("/hr/api/userProfilePage/emergencyContactInfo?eid="+(this.visitedEid == -1? localStorage.getItem("eid") : this.visitedEid.toString())).subscribe(
     (response) => {
       //this.contactList = [];
        var JsonObject = JSON.parse(JSON.stringify(response));
        //console.log(JsonObject.contacts.length);
        //console.log(JsonObject.contacts);
        for(var i = 0; i < JsonObject.contacts.length; i++){
          this.contactList.push(JsonObject.contacts[i]);
          //console.log(i);
          //console.log(JsonObject.contacts[i])
          
        }
        //console.log(this.contactList);
        //if-else
        /*
        this.emergencyContactForm.setValue({
          firstName: JsonObject.contacts[0].firstName,
          phone: JsonObject.contacts[0].phoneNumber,
          address: JsonObject.contacts[0].address
        });
        */
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
  /*
  submitForm(form: FormGroup) {
    this.editable = false;
    var data =  {
      personalEmail: form.value["personalEmail"],
      cellPhone: form.value["cellPhone"],
      workPhone: form.value["workPhone"],
      uid: localStorage.getItem("uid")
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
  */


}
