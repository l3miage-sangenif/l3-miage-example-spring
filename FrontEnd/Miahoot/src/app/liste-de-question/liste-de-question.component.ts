import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';

import { Router } from '@angular/router';

import { Observable, of } from 'rxjs';
import { EnseignantService } from '../services/enseignant.service';
import { Miahoot } from '../models/miahoot';
import { Response } from '../models/response';

@Component({
  selector: 'app-liste-de-question',
  templateUrl: './liste-de-question.component.html',
  styleUrls: ['./liste-de-question.component.css'],
})
export class ListeDeQuestionComponent implements OnInit {

  
  idMiahoot!: number;
  miahootApresenter : any ;
  responseSelected! : Response ;

  constructor(private serviceQ : EnseignantService){

  }

  ngOnInit(): void {
    this.serviceQ.getMiahootById(this.idMiahoot).subscribe((data) => {
      this.miahootApresenter = data;
    });
  }
  
  



  /**************** Les attributs ***********************/
  public Result: boolean[] | undefined;
  @Output() answerSelected = new EventEmitter<void>();

  /**********  declaration d'une liste de question ***************/
  //  @Input() miahoot: Miahoot;

  public showResults = false;
  public miahoot: Miahoot | undefined;

  // Exemple de questionnaire

  /*********   Partie constructor *************************/

  /*constructor(private miahootService: MiahootService) {}
  ngOnInit(): void {
    this.miahoot = this.miahootService.getMiahoot();
  }*/

  /****************** Les Methodes **************************/

  submitAnswers() {
    this.showResults = true;
  }
}
