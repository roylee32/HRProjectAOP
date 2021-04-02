import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ContactInfoSectionComponent } from './contact-info-section.component';

describe('ContactInfoSectionComponent', () => {
  let component: ContactInfoSectionComponent;
  let fixture: ComponentFixture<ContactInfoSectionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ContactInfoSectionComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ContactInfoSectionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
