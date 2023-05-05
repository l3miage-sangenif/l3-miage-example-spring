import { TestBed } from '@angular/core/testing';

import { TransfertEnseignantQcmService } from './transfert-enseignant-qcm.service';

describe('TransfertEnseignantQcmService', () => {
  let service: TransfertEnseignantQcmService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TransfertEnseignantQcmService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
