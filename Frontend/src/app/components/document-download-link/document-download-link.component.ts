import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-document-download-link',
  templateUrl: './document-download-link.component.html',
  styleUrls: ['./document-download-link.component.css']
})
export class DocumentDownloadLinkComponent implements OnInit {

  constructor() { }

  @Input() document;

  ngOnInit(): void {
  }

}
