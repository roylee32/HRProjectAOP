import { TestBed } from '@angular/core/testing';

import { EmployeeguardService } from './employeeguard.service';

describe('EmployeeguardService', () => {
  let service: EmployeeguardService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(EmployeeguardService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
