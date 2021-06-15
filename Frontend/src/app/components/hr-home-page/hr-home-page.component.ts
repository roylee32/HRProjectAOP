import { Component, OnInit } from '@angular/core';
import { HttpService } from 'src/app/services/http.service';
@Component({
  selector: 'app-hr-home-page',
  templateUrl: './hr-home-page.component.html',
  styleUrls: ['./hr-home-page.component.css']
})
export class HrHomePageComponent implements OnInit {

  expand = false;
  headElements = ['', 'Name(Legal Name)', 'Work Authorization', 'Expiration Date', 'Day Left', 'Action Required'];
  visaData: any[] = [];
  employeeData: any[] = [];
  documentsData: any[] = [];
  selected: any;
  wholeName: string;
  nextSteps: any[]= [];
  isMaxed: false;
  errorMessage: string;
  nextStep1: string;
  dateDiffData: any[]= [];
  constructor(private httpService: HttpService) {}

  ngOnInit(): void {
    this.getData();
    //this.expand = false;

  }

  getData(): void{
    this.httpService.getData("/hr/api/hrVisaStatusManagement?uid="+localStorage.getItem("uid")).subscribe(
      (response) => {  
         var JsonObject = JSON.parse(JSON.stringify(response));
         this.visaData = JsonObject.visas;
         this.employeeData = JsonObject.employees;
         for(var i = 0; i < JsonObject.visas.length; i++){
          var dateleft = (new Date(JsonObject.visas[i].visaEndDate).getTime() - Date.now())/1000/60/60/24;
            console.log(dateleft);
            console.log(Date.now);
            this.dateDiffData.push(dateleft);
         }
         console.log(JsonObject.employee);
         localStorage.setItem("eid", JsonObject.employee.eid);
        this.getNextSteps();
       }
    );
    
    
    
  }

  getNextSteps():void{
    // var data1 = this.employeeData[k];
    const allPromise = [];
    this.employeeData.forEach((emp, index) => {
      allPromise.push(
        this.httpService.postData("/hr/api/hrVisaStatusManagements", emp).toPromise()
      )
    });
    Promise.all(allPromise).then(response => {
      console.log(response);
      this.nextSteps = response.map(res => res.nextSteps);
    });
  }
  /*
  getNextSteps(i): void{
    var data1 = this.employeeData[i];
    this.httpService.postData("/hr/api/hrVisaStatusManagements", data1).subscribe(
      (response: any) => {
        //var JsonObject = JSON.parse(JSON.stringify(response));
        //console.log(JsonObject.pdocs);
        for(var i = 0; i < response.pdocs.length; i++){
          this.documentsData.push(response.pdocs[i].title);
        }
        this.wholeName = response.fullName;
        this.nextSteps = response.nextSteps;
      }
    );
  }
*/
  sendEmail(i): void{
    console.log(i);
    this.errorMessage = "Sending Email ..."
    var data = {
      nextSteps: this.nextSteps[i],
      email: this.employeeData[i].email,
      fullName: this.employeeData[i].firstName +" "+ this.employeeData[i].lastName
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
