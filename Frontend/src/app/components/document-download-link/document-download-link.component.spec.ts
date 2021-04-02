import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DocumentDownloadLinkComponent } from './document-download-link.component';

describe('DocumentDownloadLinkComponent', () => {
  let component: DocumentDownloadLinkComponent;
  let fixture: ComponentFixture<DocumentDownloadLinkComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DocumentDownloadLinkComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DocumentDownloadLinkComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
