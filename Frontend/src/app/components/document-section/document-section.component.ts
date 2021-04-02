import { Component, Input, OnInit } from '@angular/core';
import { HttpService } from 'src/app/services/http.service';

@Component({
  selector: 'app-document-section',
  templateUrl: './document-section.component.html',
  styleUrls: ['./document-section.component.css']
})
export class DocumentSectionComponent implements OnInit {

  @Input() visitedEid: number;

  uploadedFiles: any;

  getData() {
    this.httpService.getData("/hr/api/documentSection?eid=" + (this.visitedEid == -1? localStorage.getItem("eid") : this.visitedEid.toString())).subscribe(
      (response) => {
        var JsonObject = JSON.parse(JSON.stringify(response));
        this.uploadedFiles = JsonObject.personalDocuments;
      }
    );
  }

  constructor(private httpService : HttpService) { }

  ngOnInit(): void {
    this.getData();
  }

}
