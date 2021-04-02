import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpService } from 'src/app/services/http.service';
@Component({
  selector: 'app-application-detail-review-documents',
  templateUrl: './application-detail-review-documents.component.html',
  styleUrls: ['./application-detail-review-documents.component.css']
})
export class ApplicationDetailReviewDocumentsComponent implements OnInit {
  eid: number;
  headElements = ['Documents', 'Date Created', 'Review'];
  pDocs: any[] = [];
  fullName: string;
  signedPath: string;
  showImage = false;
  constructor(private activateRoute: ActivatedRoute, private httpService: HttpService) { }

  ngOnInit(): void {
    this.activateRoute.params.subscribe(
      params => {
        this.eid = params["eid"];
      }
    );
    this.getData();
  }

  getData(){
    this.pDocs = [];
    this.httpService.getData("/hr/api/getDocuments/"+this.eid).subscribe(
      (response) => {
        var JsonObject = JSON.parse(JSON.stringify(response));
        
        this.pDocs = JsonObject.pdocs;
        this.fullName = JsonObject.pdocs[0].employee.firstName +" " + JsonObject.pdocs[0].employee.lastName;
        //console.log(this.fullName);
      }
    );
  }

  sendReview(i): void{
    //console.log(this.pDocs[i].path);
    var data = {
      path: this.pDocs[i].path
    }
    this.pDocs[i]['showImage'] = false;
    this.httpService.postData("/hr/api/getDocuments/editAndReview", data).subscribe(
      (response: any) => {
        var JsonObject = JSON.parse(JSON.stringify(response));
        this.signedPath = JsonObject.signedPath;
        console.log(this.signedPath);
     
        this.pDocs[i]['signedPath'] = JsonObject.signedPath;
          this.pDocs[i]['showImage'] = true

        // const fileReader = new FileReader();
        // fileReader.onload = ({target}) => {
        //   console.log('image', target.result);
        //   const result = target.result as string;
        //   this.showImage = true;
        //   this.pDocs[i]['type'] = result.split(';')[0].split(':')[1];
        //   this.pDocs[i]['signedPath'] = 'https://image.shutterstock.com/image-photo/mountains-under-mist-morning-amazing-600w-1725825019.jpg';
        //   this.pDocs[i]['showImage'] = true
        //   this.pDocs = this.pDocs.slice();
        // }
        // fileReader.readAsDataURL(new Blob('https://image.shutterstock.com/image-photo/mountains-under-mist-morning-amazing-600w-1725825019.jpg'))
      }
    );

  }

}
