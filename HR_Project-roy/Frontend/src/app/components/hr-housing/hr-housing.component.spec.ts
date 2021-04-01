import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HrHousingComponent } from './hr-housing.component';

describe('HrHousingComponent', () => {
  let component: HrHousingComponent;
  let fixture: ComponentFixture<HrHousingComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HrHousingComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HrHousingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
