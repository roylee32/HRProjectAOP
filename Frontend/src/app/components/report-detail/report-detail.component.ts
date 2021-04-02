import { formatCurrency } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { HttpService } from 'src/app/services/http.service';

@Component({
  selector: 'app-report-detail',
  templateUrl: './report-detail.component.html',
  styleUrls: ['./report-detail.component.css']
})
export class ReportDetailComponent implements OnInit {

  message: string;
  updateMessage: string;
  frid: any;
  reportInfo;
  comments;
  role = localStorage.getItem("role");
  commentForm = new FormGroup({
    comment: new FormControl("")
  });

  constructor(private httpService: HttpService, private activateRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.activateRoute.params.subscribe(
      params => {
        this.frid = params["reportId"];
      }
    );
    console.log(this.frid);
    this.getReportInfo();
  }

  getReportInfo(){
    this.httpService.getData("/hr/api/getReportInfo/"+this.frid).subscribe(
      (response) => {
        var JsonObject = JSON.parse(JSON.stringify(response));
        this.reportInfo = JsonObject.facilityReport;
        this.comments = JsonObject.facilityReportDetails;
      }
    );
  }

  submitForm(form: FormGroup){
    this.message = "Submitting ...";
    var data = {
      eid: localStorage.getItem("eid"),
      reportId: this.frid,
      comment: form.value["comment"]
    };
    this.httpService.postData("/hr/api/newReportComment", data).subscribe(
      (response) => {
        var JsonObject = JSON.parse(JSON.stringify(response));
        this.message = JsonObject.message;
        this.ngOnInit();
      }
    );
  }

  isCreator(creator: number):boolean{
    return localStorage.getItem("eid") == creator.toString();
  }

  updateCommentFunc(comment:string, frdid:number){
    this.updateMessage = "Updating ..."
    var data = {
      frdid: frdid,
      comment: comment
    };
    this.httpService.postData("/hr/api/updateReportComment", data).subscribe(
      (response) => {
        var JsonObject = JSON.parse(JSON.stringify(response));
        this.updateMessage = JsonObject.message;
        this.ngOnInit();
      }
    )
  }

}
