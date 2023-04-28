import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ResultatQCMComponent } from './resultat-qcm.component';

describe('ResultatQCMComponent', () => {
  let component: ResultatQCMComponent;
  let fixture: ComponentFixture<ResultatQCMComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ResultatQCMComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(ResultatQCMComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
