import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HrNavBarComponent } from './hr-nav-bar.component';

describe('HrNavBarComponent', () => {
  let component: HrNavBarComponent;
  let fixture: ComponentFixture<HrNavBarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HrNavBarComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HrNavBarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
