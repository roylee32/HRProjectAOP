import { TestBed } from '@angular/core/testing';

import { JWTGuardService } from './jwtguard.service';

describe('JWTGuardService', () => {
  let service: JWTGuardService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(JWTGuardService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
