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

  miahootRecupere!: Miahoot;
  idMiahoot!: number;
 // miahootApresenter : Miahoot ;
  selectedResponse! : Response ;
  

  /*Definition d'un tableau de Miahoot nommé miahoot en dure
  miahootApresente : Miahoot = {
    nom: 'Kadiin',
    questions: [
      {
        label: 'Dili',
        responses: [
          { label: 'Ami', estValide: true },
          { label: 'AB', estValide: false }
      ],
      },
      {
        label: 'Dila',
        responses: [
          { label: 'Ami', estValide: false },
          { label: 'CC', estValide: true}

      ],
      },
    ],
  };*/

  constructor(private serviceQ : EnseignantService, private route : ActivatedRoute){
    
    this.serviceQ.getMiahootById(Number(route.snapshot.paramMap.get('idMiahoot'))).subscribe(miahootRecupere=>{
      this.miahootRecupere = miahootRecupere as Miahoot
    })
    console.log(Number(route.snapshot.paramMap.get('idMiahoot')))
    //this.miahootRecupere.s

  }

  ngOnInit(): void {
    
    
  }
  
  



  /**************** Les attributs ***********************/
  public Result: boolean[] | undefined;
  @Output() answerSelected = new EventEmitter<void>();

  /**********  declaration d'une liste de question ***************/
  //  @Input() miahoot: Miahoot;

  public showResults = false;
  /*public miahoot: Miahoot | undefined;*/

  // Exemple de questionnaire

  /*********   Partie constructor *************************/

  /*constructor(private miahootService: MiahootService) {}
  ngOnInit(): void {
    this.miahoot = this.miahootService.getMiahoot();
  }*/

  /****************** Les Methodes *************************/

  submitAnswers() {
    this.showResults = true;
  }
}
