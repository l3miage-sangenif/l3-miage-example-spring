import { Injectable } from '@angular/core';
import { Miahoot } from './models/miahoot';

@Injectable({
  providedIn: 'root'
})
export class TransfertEnseignantQcmService {

  public miahoot: any

  public idUser!: string; 

  constructor() { }
}
