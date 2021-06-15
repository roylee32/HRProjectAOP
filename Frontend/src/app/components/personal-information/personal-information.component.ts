import { Component, Input, OnInit } from '@angular/core';
import { HttpService } from 'src/app/services/http.service';

@Component({
  selector: 'app-personal-information',
  templateUrl: './personal-information.component.html',
  styleUrls: ['./personal-information.component.css']
})
export class PersonalInformationComponent implements OnInit {

  constructor() { }
  ngOnInit(): void {
    if (localStorage.getItem("visitedEid") != null) {
      this.visitedEid = parseInt(localStorage.getItem("visitedEid"));
    }
   }

  visitedEid = -1;

}
