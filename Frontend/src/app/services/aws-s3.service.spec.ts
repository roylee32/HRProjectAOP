import { TestBed } from '@angular/core/testing';

import { AWSS3Service } from './aws-s3.service';

describe('AWSS3Service', () => {
  let service: AWSS3Service;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AWSS3Service);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
