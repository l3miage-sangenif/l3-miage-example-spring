import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';

import { ActivatedRoute, Router } from '@angular/router';

import { Observable, of } from 'rxjs';
import { EnseignantService } from '../services/enseignant.service';
import { Miahoot } from '../models/miahoot';
import { Response } from '../models/response';
import { PartageMiahootService } from '../partage-miahoot.service';

@Component({
  selector: 'app-liste-de-question',
  templateUrl: './liste-de-question.component.html',
  styleUrls: ['./liste-de-question.component.css'],
})
export class ListeDeQuestionComponent implements OnInit {

  miahootRecupere!: Miahoot ;
  idMiahoot!: number;

  selectedResponse! : Response ;


  constructor(private serviceQ : EnseignantService, private route : ActivatedRoute,private router:Router){

    this.serviceQ.getMiahootById(Number(route.snapshot.paramMap.get('idMiahoot'))).subscribe(miahootRecupere=>{

      this.miahootRecupere = miahootRecupere;

    })
    console.log(Number(route.snapshot.paramMap.get('idMiahoot')))


  }

  ngOnInit(): void {


  }

  public Result: boolean[] | undefined;
  @Output() answerSelected = new EventEmitter<void>();

  public showResults = false;


  submitAnswers() {
    this.showResults = true;
    this.serviceQ.saveResponses(this.miahootRecupere);
    this.router.navigateByUrl("/resultat");
  }
}
