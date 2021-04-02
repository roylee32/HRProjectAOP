import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HrEmailComponent } from './hr-email.component';

describe('HrEmailComponent', () => {
  let component: HrEmailComponent;
  let fixture: ComponentFixture<HrEmailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HrEmailComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HrEmailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
