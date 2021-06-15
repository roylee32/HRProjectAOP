import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { HttpService } from 'src/app/services/http.service';

@Component({
  selector: 'app-visa-status-management',
  templateUrl: './visa-status-management.component.html',
  styleUrls: ['./visa-status-management.component.css']
})
export class VisaStatusManagementComponent implements OnInit {
  expand = false;
  headElements = ['', 'Name(Legal Name)', 'Work Authorization', 'Expiration Date', 'Day Left', 'Action Required'];
  visaData: any[] = [];
  employeeData: any[] = [];
  documentsData: any[] = [];
  createData: any[] = [];
  dateDiffData: any[]= [];
  selected: any;
  wholeName: string;
  nextSteps: string;
  isMaxed: false;
  errorMessage: string;
  constructor(private httpService: HttpService) {}

  ngOnInit(): void {
    this.getData();
    //this.expand = false;
  }

  getData(): void{
    this.httpService.getData("/hr/api/hrVisaStatusManagement?uid="+localStorage.getItem("uid")).subscribe(
      (response) => {  
         var JsonObject = JSON.parse(JSON.stringify(response));
         //console.log(JsonObject.visas.length);
         for(var i = 0; i < JsonObject.visas.length; i++){
           //console.log(JsonObject.visas[i]);
           this.visaData.push(JsonObject.visas[i]);
           var dateleft = (new Date(JsonObject.visas[i].visaEndDate).getTime() - Date.now())/1000/60/60/24;
           console.log(dateleft);
           console.log(Date.now);
           this.dateDiffData.push(dateleft);
           //this.dateDiffData.push(JsonObject.visas[i].)
           this.employeeData.push(JsonObject.employees[i]);     
         }
       }
    );
    
  }

  maximize(v: any, i): void{
    //this.expand = false;
    this.selected = null;
    console.log(v);
  }

  minimize(v: any, i): void{
    //this.expand = true;
    this.documentsData = [];
    this.createData = [];
    this.selected = v;
    console.log(this.employeeData[i]);
    var data = this.employeeData[i];
    this.httpService.postData("/hr/api/hrVisaStatusManagements", data).subscribe(
      (response: any) => {
        //var JsonObject = JSON.parse(JSON.stringify(response));
        //console.log(JsonObject.pdocs);
        for(var i = 0; i < response.pdocs.length; i++){
          this.documentsData.push(response.pdocs[i].title);
          this.createData.push(response.pdocs[i].createDate);
        }
        this.wholeName = response.fullName;
        this.nextSteps = response.nextSteps;
      }
    );
    
  }

  sendEmail(i): void{
    this.errorMessage = "Sending Email ..."
    var data = {
      nextSteps: this.nextSteps,
      email: this.employeeData[i].email,
      fullName: this.wholeName
    }
    console.log(data);
    
    this.httpService.postData("/hr/api/hrVisaStatusManagement/sendEmail", data).subscribe(
      (response: any) => {
        if(response.errorMessage != null){
          this.errorMessage = response.errorMessage;
        }
        else{
          this.errorMessage = "Sent!";
        }
      }
    );
    
  }

  getDateDiff(i) {
    var diff = new Date(this.visaData[i].visaEndDate).getTime() - Date.now();
    console.log(diff/1000/60/60/24);
    return (diff/1000/60/60/24);
  }

  


}
