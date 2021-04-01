import { Component, Input, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { HttpService } from 'src/app/services/http.service';

@Component({
  selector: 'app-address-section',
  templateUrl: './address-section.component.html',
  styleUrls: ['./address-section.component.css']
})
export class AddressSectionComponent implements OnInit {

  @Input() visitedEid: number;

  editable = false;
  errorMessage: string;

  addressSectionForm = new FormGroup({
    line1: new FormControl(''),
    line2: new FormControl(''),
    city: new FormControl(''),
    state: new FormControl(''),
    zipcode: new FormControl('')
  })

  addressSectionInfo = {
    line1: "",
    line2: "",
    city:"",
    state:"",
    zipcode:""
  }

  constructor(private httpService: HttpService) { }

  getData() {
    this.addressSectionForm.setValue(this.addressSectionInfo);
  }

  getDataFromDB() {
    this.httpService.getData("/hr/api/addressSection?eid=" + (this.visitedEid == -1? localStorage.getItem("eid") : this.visitedEid.toString())).subscribe(
      (response) => {
        var JsonObject = JSON.parse(JSON.stringify(response));
        this.addressSectionInfo.line1 = JsonObject.line1;
        this.addressSectionInfo.line2 = JsonObject.line2;
        this.addressSectionInfo.city = JsonObject.city;
        this.addressSectionInfo.state = JsonObject.state;
        this.addressSectionInfo.zipcode = JsonObject.zipcode;
        this.getData();
      }
    );
  }

  makeEditable() {
    this.editable = true;
  }

  cancel() {
    let conf = confirm("Are you sure to discard all your changes?");
    if (conf) {
      this.getData();
      this.editable = false;
    }
    else {
      return false;
    }
  }

  ngOnInit(): void {
    this.getDataFromDB();
    this.editable = false;
  }

  submitForm(form : FormGroup) {
    this.editable = false;
    var data =  {
      line1: form.value["line1"],
      line2: form.value["line2"],
      city: form.value["city"],
      state: form.value["state"],
      zipcode: form.value["zipcode"],
      eid: (this.visitedEid == -1? localStorage.getItem("eid") : localStorage.getItem("visitedEid"))
    };
    this.httpService.postData("/hr/api/addressSection", data).subscribe(
      (response) => {
        var JsonObject = JSON.parse(JSON.stringify(response));
        if (JsonObject.errorMessage != null) {
          this.errorMessage = JsonObject.errorMessage;
        }
        else {
          location.reload;
        }
      }
    )
  }

}
