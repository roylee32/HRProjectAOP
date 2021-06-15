import { HttpResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FileUploadService } from 'src/app/services/file-upload.service';
import { HttpService } from 'src/app/services/http.service';

@Component({
  selector: 'app-employee-visa-status',
  templateUrl: './employee-visa-status.component.html',
  styleUrls: ['./employee-visa-status.component.css']
})
export class EmployeeVisaStatusComponent implements OnInit {

  isCitizenOrPR = false;

  statuses=["OPT Receipt", "OPT EAD", "I-983", "I-20", "OPT STEM Receipt", "OPT STEM EAD"];

  visaStatusInfo : any;
  visaValidDays: number;

  applicationInfo: any;

  uploadedFiles: any;

  selectedFiles: FileList;
  currentFile: File;

  currentStatusId = -1.5; // to do next

  errorMessage: string;

  getData() {
    this.httpService.getData("/hr/api/visaStatusMgmt?eid=" + localStorage.getItem("eid")).subscribe(
      (response) => {
        var JsonObject = JSON.parse(JSON.stringify(response));
        this.applicationInfo = JsonObject.application;
        this.visaStatusInfo = JsonObject.visaStatus;
        this.uploadedFiles = JsonObject.personalDocuments;
        console.log(JsonObject);
        if (this.visaStatusInfo == null) {
          this.isCitizenOrPR = true;
          return;
        }
        if(this.applicationInfo == null){
          return;
        }
        let currentStatus = this.applicationInfo.status.split("|");

        if ("OPT Receipt" === currentStatus[0]) {
          this.currentStatusId = 1;
        }
        else if ("OPT EAD" === currentStatus[0]) {
          this.currentStatusId = 2;
        }
        else if ("I-983" === currentStatus[0]) {
          this.currentStatusId = 3;
        }
        else if ("I-20" === currentStatus[0]) {
          this.currentStatusId = 4;
        }
        else if ("OPT STEM Receipt" === currentStatus[0]) {
          this.currentStatusId = 5;
        }
        else if ("OPT STEM EAD" === currentStatus[0]) {
          this.currentStatusId = 6;
        }
        else {
          this.currentStatusId = -1;
        }
        if ("pending" === currentStatus[1]) {
          this.currentStatusId -= 0.5;
        }
        this.visaValidDays = this.getVisaValidDays();
      }
    );
  }

  getVisaValidDays() {
    let diff = Date.parse(this.visaStatusInfo.visaEndDate) - Date.now();
    return Math.floor(diff/(1000 * 60 * 60 * 24));
  }

  getColor(i : number) {
    if (i == Math.floor(this.currentStatusId)) {
      return 'color : red';
    }
    else if (i > Math.floor(this.currentStatusId)) {
      return 'color : gray';
    }
    else {
      return '';
    }
  }

  getNextStep() {
    if (this.currentStatusId == 0.5) {
      return "Waiting for HR to approve your OPT Receipt";
    }
    else if (this.currentStatusId == 1) {
      return "Please upload a copy of your OPT EAD";
    }
    else if (this.currentStatusId == 1.5) {
      return "Waiting for HR to approve your OPT EAD";
    }
    else if (this.currentStatusId == 2) {
      if (this.visaValidDays > 100) {
        return "Nothing to do now. Come back less than 100 days before your EAD expiration date to fill I-983.";
      }
      else {
        return "Please download and fill you I-983 form";
      }
    }
    else if (this.currentStatusId == 2.5) {
      return "Waiting for HR to approve and sign I-983";
    }
    else if (this.currentStatusId == 3) {
      return "Please send the I-983 with all necessary documents to your school and upload the new I-20";
    }
    else if (this.currentStatusId == 3.5) {
      return "Please wait for HR to approve your new I-20";
    }
    else if (this.currentStatusId == 4) {
      return "Please upload your OPT STEM Receipt";
    }
    else if (this.currentStatusId == 4.5) {
      return "Waiting for HR to approve your OPT STEM Receipt";
    }
    else if (this.currentStatusId == 5) {
      return "Please upload your OPT STEM EAD";
    }
    else if (this.currentStatusId == 5.5) {
      return "Waiting for HR to approve your OPT STEM EAD";
    }
    else if (this.currentStatusId == 6) {
      return "No upcoming document requirements regarding OPT";
    }
    else {
      return "Visa type is not F1.";
    }
  }

  showUpload() {
    return !(this.currentStatusId == 2 && this.visaValidDays > 100) && this.currentStatusId == Math.floor(this.currentStatusId) && !(this.currentStatusId == 6);
  }

  selectFile(event) {
    console.log("in selectFile()");
    this.selectedFiles = event.target.files;
  }

  uploadFile() {
    this.currentFile = this.selectedFiles.item(0);
    let fileType = this.statuses[this.currentStatusId];
    this.uploadService.pushFileToStorage(this.currentFile, fileType).subscribe(
      (event) => {
        if (event instanceof HttpResponse){
          console.log(event);
          if (event.status == 200) {
            this.errorMessage = "Upload succeeded.";
            this.applicationInfo.status = this.statuses[Math.ceil(this.currentStatusId)] + "|pending";
            this.updateApplication();
            location.reload;
          }
          else {
            this.errorMessage = "Something went wrong. Please try again later.";
          }
        }
      });
      this.selectedFiles = undefined;
  }

  updateApplication() {
    var data = {
      uid: localStorage.getItem("uid"),
      application: this.applicationInfo
    }
    this.httpService.postData("/hr/api/visaStatusMgmt", data).subscribe(
      (response) => {
        var JsonObject = JSON.parse(JSON.stringify(response));
        if (JsonObject.errorMessage != null) {
          this.errorMessage = JsonObject.errorMessage;
        }
        else {
          location.reload;
        }
      }
    );
  }

  constructor(private httpService: HttpService, private uploadService: FileUploadService) { }

  ngOnInit(): void {
    this.getData();
  }

}
