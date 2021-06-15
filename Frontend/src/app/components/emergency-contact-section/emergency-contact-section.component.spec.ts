import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EmergencyContactSectionComponent } from './emergency-contact-section.component';

describe('EmergencyContactSectionComponent', () => {
  let component: EmergencyContactSectionComponent;
  let fixture: ComponentFixture<EmergencyContactSectionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EmergencyContactSectionComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EmergencyContactSectionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
