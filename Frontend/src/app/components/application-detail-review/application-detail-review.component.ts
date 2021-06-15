import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { HttpService } from 'src/app/services/http.service';

@Component({
  selector: 'app-application-detail-review',
  templateUrl: './application-detail-review.component.html',
  styleUrls: ['./application-detail-review.component.css']
})
export class ApplicationDetailReviewComponent implements OnInit {

  aid: number;
  personalInfo;
  addressInfo;
  visaInfo;
  referenceInfo;
  emergencyInfo;
  message;
  avatar;

  personalInfoForm = new FormGroup({
    firstName: new FormControl(""),
    lastName: new FormControl(""),
    middleName: new FormControl(""),
    addressLine1: new FormControl(""),
    addressLine2: new FormControl(""),
    city: new FormControl(""),
    zipcode: new FormControl(""),
    state: new FormControl(""),
    cellPhone: new FormControl(""),
    alternatePhone: new FormControl(""),
    carInfo: new FormControl(""),
    ssn: new FormControl(""),
    DOB: new FormControl(""),
    gender: new FormControl(""),
    DL: new FormControl(""),
    email: new FormControl("")
  });

  visaInfoForm = new FormGroup({
    visaType: new FormControl(""),
    visaStartDate: new FormControl(""),
    visaEndDate: new FormControl("")
  });

  referenceInfoForm = new FormGroup({
    referenceFirstName: new FormControl(""),
    referenceLastName: new FormControl(""),
    referenceMiddleName: new FormControl(""),
    referencePhone: new FormControl(""),
    referenceAddress: new FormControl(""),
    referenceEmail: new FormControl(""),
    referenceRelationShip: new FormControl("")
  });

  emergencyInfoForm = new FormGroup({
    emergencyFirstName: new FormControl(""),
    emergencyLastName: new FormControl(""),
    emergencyMiddleName: new FormControl(""),
    emergencyPhone: new FormControl(""),
    emergencyEmail: new FormControl(""),
    emergencyRelationShip: new FormControl("")
  });

  commentForm = new FormGroup({
    comment: new FormControl("")
  });

  constructor(private activateRoute: ActivatedRoute, private httpService: HttpService) { }

  ngOnInit(): void {
    this.activateRoute.params.subscribe(
      params => {
        this.aid = params["aid"];
      }
    );
    this.getApplicationDetail();
  }

  getApplicationDetail(){
    this.httpService.getData("/hr/api/getApplicationDetail/"+this.aid).subscribe(
      (response) => {
        var JsonObject = JSON.parse(JSON.stringify(response));
        console.log(JsonObject);
        this.personalInfo = JsonObject.application.employee;
        this.addressInfo = JsonObject.addresses;
        this.visaInfo = JsonObject.visaStatuses;
        this.referenceInfo = JsonObject.referenceContact;
        this.emergencyInfo = JsonObject.emergenceContact;
        this.avatar = JsonObject.avatarURL;
        this.setValues();
      }
    );
  }

  setValues(){
    this.personalInfoForm.setValue({
      firstName: this.personalInfo.firstName,
      lastName: this.personalInfo.lastName,
      middleName: this.personalInfo.middleName,
      addressLine1: this.addressInfo[0].addressLine1,
      addressLine2: this.addressInfo[0].addressLine2,
      city: this.addressInfo[0].city,
      zipcode: this.addressInfo[0].zipcode,
      state: this.addressInfo[0].stateName,
      cellPhone: this.personalInfo.cellPhone,
      alternatePhone: this.personalInfo.alternatePhone,
      carInfo: this.personalInfo.car,
      ssn: this.personalInfo.ssn,
      DOB: this.personalInfo.dob,
      gender: this.personalInfo.gender,
      DL: this.personalInfo.dlnumber,
      email: this.personalInfo.email
    });
    if (this.visaInfo.length != 0){
      this.visaInfoForm.setValue({
        visaType: this.visaInfo[0].visaType,
        visaStartDate: this.visaInfo[0].visaStartDate,
        visaEndDate: this.visaInfo[0].visaEndDate
      });
    }
    if (this.emergencyInfo.length != 0){
      this.emergencyInfoForm.setValue({
        emergencyFirstName: this.emergencyInfo[0].firstName,
        emergencyLastName: this.emergencyInfo[0].lastName,
        emergencyMiddleName: this.emergencyInfo[0].middleName,
        emergencyPhone: this.emergencyInfo[0].phoneNumber,
        emergencyEmail: this.emergencyInfo[0].email,
        emergencyRelationShip: this.emergencyInfo[0].relationship
      });
    }
    if (this.referenceInfo.length != 0){
      this.referenceInfoForm.setValue({
        referenceFirstName:this.referenceInfo[0].firstName,
        referenceLastName: this.referenceInfo[0].lastName,
        referenceMiddleName: this.referenceInfo[0].middleName,
        referencePhone: this.referenceInfo[0].phoneNumber,
        referenceAddress: this.referenceInfo[0].address,
        referenceEmail: this.referenceInfo[0].email,
        referenceRelationShip:this.referenceInfo[0].relationship
      });
    }
  }

  approveApplication(){
    this.message = "Processing ...";
    var data = {
      aid: this.aid
    }
    this.httpService.postData("/hr/api/approveApplication", data).subscribe(
      (response) => {
        var JsonObject = JSON.parse(JSON.stringify(response));
        this.message = JsonObject.message;
      }
    );
  }

  rejectApplication(){
    this.message = "Processing ...";
    var data = {
      aid: this.aid
    }
    this.httpService.postData("/hr/api/rejectApplication", data).subscribe(
      (response) => {
        var JsonObject = JSON.parse(JSON.stringify(response));
        this.message = JsonObject.message;
      }
    );
  }

  commentApplication(form: FormGroup){
    this.message = "Processing ...";
    var data = {
      aid: this.aid,
      comment: form.value["comment"]
    }
    this.httpService.postData("/hr/api/commentApplication", data).subscribe(
      (response) => {
        var JsonObject = JSON.parse(JSON.stringify(response));
        this.message = JsonObject.message;
      }
    );
  }

}
