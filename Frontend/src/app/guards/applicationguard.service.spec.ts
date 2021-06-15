import { TestBed } from '@angular/core/testing';

import { ApplicationguardService } from './applicationguard.service';

describe('ApplicationguardService', () => {
  let service: ApplicationguardService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ApplicationguardService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
