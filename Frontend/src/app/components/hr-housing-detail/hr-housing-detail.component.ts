import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpService } from 'src/app/services/http.service';

@Component({
  selector: 'app-hr-housing-detail',
  templateUrl: './hr-housing-detail.component.html',
  styleUrls: ['./hr-housing-detail.component.css']
})
export class HrHousingDetailComponent implements OnInit {

  hid: number;
  houseInfo;
  facilityInfo;
  residents;
  facilityReports;

  constructor(private activateRoute: ActivatedRoute, private httpService: HttpService, private router: Router) { }

  ngOnInit(): void {
    this.activateRoute.params.subscribe(
      params => {
        this.hid = params["hid"];
      }
    );
    this.getHouseInfo();
  }

  getHouseInfo(){
    this.httpService.getData("/hr/api/getHouseInfoByHid/"+this.hid).subscribe(
      (response) => {
        var JsonObject = JSON.parse(JSON.stringify(response));
        this.houseInfo = JsonObject.house;
        this.facilityInfo = JsonObject.facilities;
        this.residents = JsonObject.residents;
        this.facilityReports = JsonObject.facilityReports;
      }
    );
  }

  seeDetail(eid: number) {
    localStorage.setItem("visitedEid", eid.toString());
    this.router.navigate(["personalInformation"]);
  }

}
