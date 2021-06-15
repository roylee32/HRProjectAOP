import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-personal-doc-download',
  templateUrl: './personal-doc-download.component.html',
  styleUrls: ['./personal-doc-download.component.css']
})
export class PersonalDocDownloadComponent implements OnInit {

  @Input() document;

  constructor() { }

  ngOnInit(): void {
  }

}
