import { HttpResponse } from '@angular/common/http';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { FileUploadService } from 'src/app/services/file-upload.service';
import { HttpService } from 'src/app/services/http.service';

@Component({
  selector: 'app-name-section',
  templateUrl: './name-section.component.html',
  styleUrls: ['./name-section.component.css']
})
export class NameSectionComponent implements OnInit {

  @Input() visitedEid: number;

  avartarUrl: string;

  selectedAvatarFiles: FileList;
  currentAvatarFiles: File;

  editable = false;
  errorMessage: string;
  nameSectionForm = new FormGroup({
    firstName:new FormControl(''),
    middleName:new FormControl(''),
    lastName:new FormControl(''),
    birthday: new FormControl(''),
    gender: new FormControl('')
  })

  constructor(private httpService: HttpService, private uploadService: FileUploadService) { }

  ngOnInit(): void {
    this.getData();
    this.editable = false;
    this.getDefaultAvartar();
  }

  getDefaultAvartar() {
    this.httpService.getData("hr/api/getDefaultAvatar").subscribe(
      (response) => {
        var JsonObject = JSON.parse(JSON.stringify(response));
        this.avartarUrl = JsonObject.signedUrl;
      }
    );
  }

  getData(): void {
     this.httpService.getData("/hr/api/userProfilePage/userNameInfo?eid="+(this.visitedEid == -1? localStorage.getItem("eid") : this.visitedEid.toString())).subscribe(
      (response) => {
         var JsonObject = JSON.parse(JSON.stringify(response));
         //if-else
         this.nameSectionForm.setValue({
           firstName: JsonObject.firstName,
           middleName: JsonObject.middleName,
           lastName: JsonObject.lastName,
           birthday: JsonObject.birthday,
           gender: JsonObject.gender
         });
         if (JsonObject.avartarUrl != null) {
          this.avartarUrl = JsonObject.avartarUrl;
         }
       }
    );
  }



  makeEditable():void {
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

  selectAvatarFile(event){
    console.log("In selectAvatarFile()");
    this.selectedAvatarFiles = event.target.files;
  }

  uploadAvatarFile(){
    this.currentAvatarFiles = this.selectedAvatarFiles.item(0);
    this.uploadService.pushFileToStorage(this.currentAvatarFiles, "avatar").subscribe(
      (event) => {
        if (event instanceof HttpResponse){
          console.log(event);
        }
      });
      this.selectedAvatarFiles = undefined;
  }

  submitForm(form: FormGroup) {
    this.editable = false;
    var data =  {
      firstName: form.value["firstName"],
      middleName: form.value["middleName"],
      lastName: form.value["lastName"],
      birthday: form.value["birthday"],
      gender: form.value["gender"],
      eid: (this.visitedEid == -1? localStorage.getItem("eid") : this.visitedEid.toString())
    };
    console.log(data)
    this.httpService.postData("/hr/api/userProfilePage/userNameInfo", data).subscribe(
      (data) => {
        var JsonObject = JSON.parse(JSON.stringify(data));
        if (JsonObject.errorMessage != null){
          this.errorMessage = JsonObject.errorMessage;
          //Set necessary form inputs as empty string.
        }
        else{
          location.reload;
        }
      }
    );
    if (this.selectedAvatarFiles != null){
      this.uploadAvatarFile();
    }
  }

  getAge() {
    var diff = Date.now() - Date.parse(this.nameSectionForm.value["birthday"]);
    return ((new Date(diff)).getFullYear() - 1970);
  }

}
