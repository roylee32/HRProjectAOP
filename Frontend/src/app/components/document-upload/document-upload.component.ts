import { HttpResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FileUploadService } from 'src/app/services/file-upload.service';
import { HttpService } from 'src/app/services/http.service';

@Component({
  selector: 'app-document-upload',
  templateUrl: './document-upload.component.html',
  styleUrls: ['./document-upload.component.css']
})
export class DocumentUploadComponent implements OnInit {

  selectedFiles: FileList;
  currentFileUpload: File;

  constructor(private httpService: HttpService, private uploadService: FileUploadService ){ }

  documents: any;
  message;

  ngOnInit(): void {
    this.httpService.getData("/hr/api/getDigitalDocuments").subscribe(
      (response) =>{
        var JsonObject = JSON.parse(JSON.stringify(response));
        this.documents = JsonObject.digitalDocuments;
      }
    );
  }

  selectFile(event){
    this.selectedFiles = event.target.files;
  }

  upload(){
    this.message = "File Uploading ...";
    this.currentFileUpload = this.selectedFiles.item(0);
    this.uploadService.pushFileToStorage(this.currentFileUpload, "onboardingDocument").subscribe(
      (event) => {
        if (event instanceof HttpResponse){
          if (event.status == 200){
            this.message = "File Upload Complete.";
          } else {
            this.message = "Something went wrong, please try again later.";
          }
        }
      }
    );
  }

}
