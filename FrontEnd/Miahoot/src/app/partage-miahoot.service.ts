import { Injectable } from '@angular/core';
import { Miahoot } from './models/miahoot';

@Injectable({
  providedIn: 'root'
})
export class PartageMiahootService {

  miahoot!: Miahoot;

  constructor() { }
}
