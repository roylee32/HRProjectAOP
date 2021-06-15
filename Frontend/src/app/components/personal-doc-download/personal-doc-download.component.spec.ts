import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PersonalDocDownloadComponent } from './personal-doc-download.component';

describe('PersonalDocDownloadComponent', () => {
  let component: PersonalDocDownloadComponent;
  let fixture: ComponentFixture<PersonalDocDownloadComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PersonalDocDownloadComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PersonalDocDownloadComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
