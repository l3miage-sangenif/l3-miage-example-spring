import { TestBed } from '@angular/core/testing';

import { PartageMiahootService } from './partage-miahoot.service';

describe('PartageMiahootService', () => {
  let service: PartageMiahootService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PartageMiahootService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
