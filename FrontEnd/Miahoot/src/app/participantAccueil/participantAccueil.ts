import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormsModule } from '@angular/forms';

import { ActivatedRoute, Router } from '@angular/router';

import { Observable, of } from 'rxjs';
import { EnseignantService } from '../services/enseignant.service';
import { Miahoot } from '../models/miahoot';
import { Response } from '../models/response';
import { PartageMiahootService } from '../partage-miahoot.service';

@Component({
  selector: 'app-liste-de-question',
  templateUrl: './participantAccueil.html',
  styleUrls: ['./participantAccueil.css'],
})
export class ParticipantAccueil implements OnInit {
  public value!: Number;
  

  ngOnInit(): void {
  }
  
}