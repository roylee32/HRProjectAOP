import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ApplicationDetailReviewDocumentsComponent } from './application-detail-review-documents.component';

describe('ApplicationDetailReviewDocumentsComponent', () => {
  let component: ApplicationDetailReviewDocumentsComponent;
  let fixture: ComponentFixture<ApplicationDetailReviewDocumentsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ApplicationDetailReviewDocumentsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ApplicationDetailReviewDocumentsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
