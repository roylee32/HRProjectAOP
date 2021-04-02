import { HttpResponse } from '@angular/common/http';
import { ThrowStmt } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { AWSS3Service } from 'src/app/services/aws-s3.service';
import { FileUploadService } from 'src/app/services/file-upload.service';
import { HttpService } from 'src/app/services/http.service';

@Component({
  selector: 'app-onboarding',
  templateUrl: './onboarding.component.html',
  styleUrls: ['./onboarding.component.css']
})
export class OnboardingComponent implements OnInit {

  eid:string;
  canSubmit:boolean = true;
  errorMessage: string;
  citizenOrPR: string;
  VisaType: string;
  ifHaveDL: string;
  ifHaveReference: string;
  defaultAvatarUrl: string;
  email = localStorage.getItem("email");
  creating:boolean = true;
  personalInfo;
  addressInfo;
  visaInfo;
  referenceInfo;
  emergencyInfo;
  message;

  selectedAvatarFiles: FileList;
  currentAvatarFiles: File;

  selectedVisaFiles: FileList;
  currentVisaFile: File;

  selectedDLFiles: FileList;
  currentDLFile : File;


  onboardingForm = new FormGroup({
    // avatar: new FormControl(""),
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
    isCitizenOrPR: new FormControl(""),
    visaType: new FormControl(""),
    visaStartDate: new FormControl(""),
    visaEndDate: new FormControl(""),
    // visaUpload: new FormControl(""),
    haveDL: new FormControl(""),
    DL: new FormControl(""),
    DLExpiration: new FormControl(""),
    // DLUpload: new FormControl(""),
    haveReference: new FormControl(""),
    referenceFirstName: new FormControl(""),
    referenceLastName: new FormControl(""),
    referenceMiddleName: new FormControl(""),
    referencePhone: new FormControl(""),
    referenceAddress: new FormControl(""),
    referenceEmail: new FormControl(""),
    referenceRelationShip: new FormControl(""),
    emergencyFirstName: new FormControl(""),
    emergencyLastName: new FormControl(""),
    emergencyMiddleName: new FormControl(""),
    emergencyPhone: new FormControl(""),
    emergencyEmail: new FormControl(""),
    emergencyRelationShip: new FormControl("")
  });

  constructor(private httpService: HttpService, private uploadService: FileUploadService, private router: Router) { }

  ngOnInit(): void {
    this.getApplicationInfo();
  }

  getApplicationInfo(){
    if (localStorage.getItem("email") == null){
      this.router.navigate(["login"]);
    }
    this.httpService.getData("/hr/api/getEmployeeApplicationInfo/"+localStorage.getItem("email")).subscribe(
      (response) => {
        var JsonObject = JSON.parse(JSON.stringify(response));
        console.log(JsonObject.application);
        if (JsonObject.application != null){
          this.creating = false;
          localStorage.setItem("eid", JsonObject.application.employee.eid);
          localStorage.setItem("aid", JsonObject.application.id);
          localStorage.setItem("applicationStatus", JsonObject.application.status);
          if (JsonObject.application.status == "approved"){
            this.router.navigate(["employeeHomePage"]);
          } else {
            this.errorMessage = JsonObject.application.comments;
            this.personalInfo = JsonObject.application.employee;
            this.addressInfo = JsonObject.addresses;
            this.visaInfo = JsonObject.visaStatuses;
            this.referenceInfo = JsonObject.referenceContact;
            this.emergencyInfo = JsonObject.emergenceContact;
            this.defaultAvatarUrl = JsonObject.avatarURL;
            this.setValues();
            if (JsonObject.application.status == "pending"){
              this.canSubmit = false;
            }
            if (JsonObject.application.status == "action needed"){
              this.canSubmit = true;
            }
          }
        } else {
          this.creating = true;
          this.citizenOrPR = "Yes";
          this.VisaType = "H1-B";
          this.ifHaveDL = "No";
          this.ifHaveReference="No";
          this.httpService.getData("/hr/api/getDefaultAvatar").subscribe(
            (response) => {
              console.log(response);
              var JsonObject = JSON.parse(JSON.stringify(response));
              this.defaultAvatarUrl = JsonObject.signedUrl;
            }
          );
        }
      }
    );
  }

  setValues(){
    var data = {
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
      emergencyFirstName: this.emergencyInfo[0].firstName,
      emergencyLastName: this.emergencyInfo[0].lastName,
      emergencyMiddleName: this.emergencyInfo[0].middleName,
      emergencyPhone: this.emergencyInfo[0].phoneNumber,
      emergencyEmail: this.emergencyInfo[0].email,
      emergencyRelationShip: this.emergencyInfo[0].relationship
    }

    if (this.personalInfo.dlnumber != null && this.personalInfo.dlexpiration != null){
      this.ifHaveDL = "Yes";
      data["haveDL"] = "Yes";
      data["DL"] = this.personalInfo.dlnumber;
      data["DLExpiration"] = this.personalInfo.dlexpiration;
    } else {
      this.ifHaveDL = "No";
      data["haveDL"] = "No";
      data["DL"] = "";
      data["DLExpiration"] = "";
    }
    
    if (this.visaInfo.length != 0){
      this.citizenOrPR = "No";
      this.VisaType = this.visaInfo[0].visaType;
      data["isCitizenOrPR"] = "No";
      data["visaType"] = this.visaInfo[0].visaType;
      data["visaStartDate"] = this.visaInfo[0].visaStartDate;
      data["visaEndDate"] = this.visaInfo[0].visaEndDate;
    } else {
      this.citizenOrPR = "Yes";
      data["isCitizenOrPR"] = "Yes";
      data["visaType"] = "";
      data["visaStartDate"] = "";
      data["visaEndDate"] = "";
    }

    if (this.referenceInfo.length != 0){
      this.ifHaveReference = "Yes";
      data["haveReference"] = "Yes";
      data["referenceFirstName"] = this.referenceInfo[0].firstName;
      data["referenceLastName"] = this.referenceInfo[0].lastName;
      data["referenceMiddleName"] = this.referenceInfo[0].middleName;
      data["referencePhone"] = this.referenceInfo[0].phoneNumber;
      data["referenceAddress"] = this.referenceInfo[0].address;
      data["referenceEmail"] = this.referenceInfo[0].email;
      data["referenceRelationShip"] = this.referenceInfo[0].relationship;
    } else {
      this.ifHaveReference = "No";
      data["haveReference"] = "No";
      data["referenceFirstName"] = "";
      data["referenceLastName"] = "";
      data["referenceMiddleName"] = "";
      data["referencePhone"] = "";
      data["referenceAddress"] = "";
      data["referenceEmail"] = "";
      data["referenceRelationShip"] = "";
    }
    console.log(data);
    this.onboardingForm.setValue(data);
  }


  selectAvatarFile(event){
    console.log("In selectAvatarFile()");
    this.selectedAvatarFiles = event.target.files;
  }

  uploadAvatarFile(){
    this.currentAvatarFiles = this.selectedAvatarFiles.item(0);
    this.uploadService.pushFileToStorage(this.currentAvatarFiles, "avatar").subscribe(
      (event) => {
        if (event instanceof HttpResponse){
          console.log(event);
        }
      });
      this.selectedAvatarFiles = undefined;
  }

  selectVisaFile(event){
    console.log("In selectVisaFile()");
    this.selectedVisaFiles =  event.target.files;
  }

  uploadVisaFile(){
    this.currentVisaFile = this.selectedVisaFiles.item(0);
    this.uploadService.pushFileToStorage(this.currentVisaFile, "visa").subscribe(
      (event) => {
        if (event instanceof HttpResponse){
          console.log(event);
        }
      });
      this.selectedVisaFiles = undefined;
  }

  selectDLFile(event){
    console.log("In selectDLFile()");
    this.selectedDLFiles = event.target.files;
  }

  uploadDLFile(){
    this.currentDLFile = this.selectedDLFiles.item(0);
    this.uploadService.pushFileToStorage(this.currentDLFile, "DL").subscribe(
      (event) => {
        if (event instanceof HttpResponse){
          console.log(event);
        }
      });
      this.selectedDLFiles = undefined;
  }

  submitForm(form: FormGroup){
    this.message = "Processing ..."
    console.log(form.value["DOB"]);
    var requestBody = {
      uid: localStorage.getItem("uid"),
      firstName: form.value["firstName"],
      lastName: form.value["lastName"],
      middleName: form.value["middleName"],
      addressLine1: form.value["addressLine1"],
      addressLine2: form.value["addressLine2"],
      city: form.value["city"],
      zipcode: form.value["zipcode"],
      state: form.value["state"],
      cellPhone: form.value["cellPhone"],
      alternatePhone: form.value["alternatePhone"],
      carInfo: form.value["carInfo"],
      ssn: form.value["ssn"],
      birthday: form.value["DOB"],
      gender: form.value["gender"],
      isCitizenOrPR: form.value["isCitizenOrPR"],
      visaType: form.value["visaType"],
      visaStartDate: form.value["visaStartDate"],
      visaEndDate: form.value["visaEndDate"],
      DL: form.value["DL"],
      DLExpiration: form.value["DLExpiration"],
      haveReference: form.value["haveReference"],
      referenceFirstName: form.value["referenceFirstName"],
      referenceLastName: form.value["referenceLastName"],
      referenceMiddleName: form.value["referenceMiddleName"],
      referencePhone: form.value["referencePhone"],
      referenceAddress: form.value["referenceAddress"],
      referenceEmail: form.value["referenceEmail"],
      referenceRelationShip: form.value["referenceRelationShip"],
      emergencyFirstName: form.value["emergencyFirstName"],
      emergencyLastName: form.value["emergencyLastName"],
      emergencyMiddleName: form.value["emergencyMiddleName"],
      emergencyPhone: form.value["emergencyPhone"],
      emergencyEmail: form.value["emergencyEmail"],
      emergencyRelationShip: form.value["emergencyRelationShip"]
    };
    if (this.creating){
      this.httpService.postData("hr/api/onboarding", requestBody).subscribe(
        (response) => {
          var JsonObject = JSON.parse(JSON.stringify(response));
          console.log(JsonObject);
          localStorage.setItem("eid", JsonObject.eid);
          if (this.selectedAvatarFiles != null){
            this.uploadAvatarFile();
          }
          if (this.selectedVisaFiles != null){
            this.uploadVisaFile();
          }
          if (this.selectedDLFiles != null){
            this.uploadDLFile();
          }
          this.message = JsonObject.message;
          this.router.navigate(["documentUpload"]);
        });
      } else{
        this.httpService.postData("hr/api/updateOnboardApplication/" + localStorage.getItem("aid"), requestBody).subscribe(
          (response) => {
            var JsonObject = JSON.parse(JSON.stringify(response));
            if (this.selectedAvatarFiles != null){
              this.uploadAvatarFile();
            }
            if (this.selectedVisaFiles != null){
              this.uploadVisaFile();
            }
            if (this.selectedDLFiles != null){
              this.uploadDLFile();
            }
            this.message = JsonObject.message;
            this.ngOnInit();
          }
        );
      }
  }
}
