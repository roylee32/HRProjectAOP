import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HttpService } from 'src/app/services/http.service';

@Component({
  selector: 'app-hr-employee-profile',
  templateUrl: './hr-employee-profile.component.html',
  styleUrls: ['./hr-employee-profile.component.css']
})
export class HrEmployeeProfileComponent implements OnInit {

  employees: any;
  
  searchString="";

  inSearchResult(employee: any) {
    if (employee.employeeInfo.firstName.includes(this.searchString) || employee.employeeInfo.lastName.includes(this.searchString)) {
      return true;
    }
    else if (employee.employeeInfo.eid.toString() === this.searchString.toString()) {
      return true;
    }
    return false;
  }

  getData() {
    this.httpService.getData("hr/api/getAllEmployees").subscribe(
      (response) => {
        var JsonObject = JSON.parse(JSON.stringify(response));
        this.employees = JsonObject.profiles;
        console.log(this.employees);
      }
    );
  }

  seeDetail(eid: number) {
    localStorage.setItem("visitedEid", eid.toString());
    this.router.navigate(["personalInformation"]);
  }

  constructor(private httpService : HttpService, private router: Router) { }

  ngOnInit(): void {
    this.getData();
  }

}
