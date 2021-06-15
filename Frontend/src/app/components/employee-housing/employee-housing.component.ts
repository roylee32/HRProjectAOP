import { ThrowStmt } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { HttpService } from 'src/app/services/http.service';

@Component({
  selector: 'app-employee-housing',
  templateUrl: './employee-housing.component.html',
  styleUrls: ['./employee-housing.component.css']
})
export class EmployeeHousingComponent implements OnInit {

  hid:number;
  message: string;
  address: string;
  residents;
  reports;

  reportForm = new FormGroup({
    title: new FormControl(""),
    description: new FormControl("")
  })

  constructor(private httpService: HttpService) { }

  ngOnInit(): void {
    this.getHouseInfo();
  }

  getHouseInfo(){
    this.httpService.getData("/hr/api/getHouseInfo/"+localStorage.getItem("eid")).subscribe(
      (response) => {
        var JsonObject = JSON.parse(JSON.stringify(response));
        this.hid = JsonObject.hid;
        this.address = JsonObject.address;
        this.residents = JsonObject.residents;
        this.reports = JsonObject.facilityReports;
      }
    );
  }

  submitForm(form: FormGroup){
    this.message = "Submitting ..."
    var data = {
      hid: this.hid,
      eid: localStorage.getItem("eid"),
      title: form.value["title"],
      description: form.value["description"]
    };
    this.httpService.postData("/hr/api/newFacilityReport", data).subscribe(
      (response) => {
        var JsonObject = JSON.parse(JSON.stringify(response));
        this.message = JsonObject.message;
        this.ngOnInit();
      }
    );
  }

}
