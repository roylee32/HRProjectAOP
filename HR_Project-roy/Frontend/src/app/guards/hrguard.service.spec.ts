import { TestBed } from '@angular/core/testing';

import { HrguardService } from './hrguard.service';

describe('HrguardService', () => {
  let service: HrguardService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(HrguardService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
