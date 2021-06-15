import { formatCurrency, formatNumber } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { HttpService } from 'src/app/services/http.service';

@Component({
  selector: 'app-hr-housing',
  templateUrl: './hr-housing.component.html',
  styleUrls: ['./hr-housing.component.css']
})
export class HrHousingComponent implements OnInit {

  message: string;
  houses;
  sizeList;

  newHouseForm = new FormGroup({
    houseAddress: new FormControl(""),
    size: new FormControl(""),
    firstName: new FormControl(""),
    lastName: new FormControl(""),
    middleName: new FormControl(""),
    phoneNumber: new FormControl(""),
    email: new FormControl(""),
    landlordAddress: new FormControl("")
  });

  constructor(private httpService: HttpService) { }

  ngOnInit(): void {
    this.getAllHouseInfo();
  }

  getAllHouseInfo(){
    this.httpService.getData("/hr/api/getAllHouseInfo").subscribe(
      (response) => {
        var JsonObject = JSON.parse(JSON.stringify(response));
        this.houses = JsonObject.houses;
        this.sizeList = JsonObject.sizeList;
      }
    );
  }

  submitForm(form: FormGroup){
    this.message = "Adding ... "
    var data = {
      houseAddress: form.value["houseAddress"],
      size: form.value["size"],
      firstName: form.value["firstName"],
      lastName: form.value["lastName"],
      middleName: form.value["middleName"],
      phoneNumber: form.value["phoneNumber"],
      email: form.value["email"],
      landlordAddress: form.valid["landlordAddress"]
    }
    this.httpService.postData("/hr/api/addNewHouse", data).subscribe(
      (response) => {
        var JsonObject = JSON.parse(JSON.stringify(response));
        this.message = JsonObject.message;
        this.ngOnInit();
      }
    );
  }

}
