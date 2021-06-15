import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ApplicationDetailReviewComponent } from './application-detail-review.component';

describe('ApplicationDetailReviewComponent', () => {
  let component: ApplicationDetailReviewComponent;
  let fixture: ComponentFixture<ApplicationDetailReviewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ApplicationDetailReviewComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ApplicationDetailReviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
