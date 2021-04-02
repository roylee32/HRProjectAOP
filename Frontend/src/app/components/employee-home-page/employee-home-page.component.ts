import { Component, OnInit } from '@angular/core';
import { HttpService } from 'src/app/services/http.service';
@Component({
  selector: 'app-employee-home-page',
  templateUrl: './employee-home-page.component.html',
  styleUrls: ['./employee-home-page.component.css']
})
export class EmployeeHomePageComponent implements OnInit {

  fullName: string;
  
  constructor(private httpService: HttpService) { }

  ngOnInit(): void {
    this.getData();
  }

  getData(): void {
    console.log(localStorage.getItem("eid"));
    this.httpService.getData("/hr/api/employeeHomePage/employeeInfo?eid="+(localStorage.getItem("eid"))).subscribe(
     (response) => {
        var JsonObject = JSON.parse(JSON.stringify(response));
        console.log(JsonObject.employee);
        this.fullName = JsonObject.employee.firstName + " " + JsonObject.employee.lastName;
      }
   );
 }

}
