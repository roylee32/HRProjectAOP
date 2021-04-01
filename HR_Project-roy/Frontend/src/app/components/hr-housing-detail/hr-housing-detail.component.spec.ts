import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HrHousingDetailComponent } from './hr-housing-detail.component';

describe('HrHousingDetailComponent', () => {
  let component: HrHousingDetailComponent;
  let fixture: ComponentFixture<HrHousingDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HrHousingDetailComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HrHousingDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
