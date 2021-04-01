import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EmploymentSectionComponent } from './employment-section.component';

describe('EmploymentSectionComponent', () => {
  let component: EmploymentSectionComponent;
  let fixture: ComponentFixture<EmploymentSectionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EmploymentSectionComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EmploymentSectionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
