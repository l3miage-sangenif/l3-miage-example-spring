import { Component, Input, OnInit } from '@angular/core';
import { Miahoot } from '../models/miahoot';
import { Question } from '../models/question';
import { Response } from "../models/response";
import { EnseignantService } from '../services/enseignant.service';

@Component({
  selector: 'app-resultat-qcm',
  templateUrl: './resultat-qcm.component.html',
  styleUrls: ['./resultat-qcm.component.css'],
})
export class ResultatQCMComponent implements OnInit {
 miahoot: Miahoot | undefined;
  showResponses = false;
  constructor(private enseignantService:EnseignantService){}
  ngOnInit(): void {
this.miahoot=this.enseignantService.miahootAvecReponses;

  }

  getScore(): number {
    this.miahoot=this.enseignantService.miahootAvecReponses;
    const numResponses = this.miahoot?.questions.length || 0;
    let numCorrect = 0;
    this.miahoot?.questions.forEach((question) => {
      const selectedResponse = question.reponses.find((r) => r.isSelected);
      if (selectedResponse && selectedResponse.estValide) {
        numCorrect++;
      }
    });
    return (numCorrect / numResponses) * 100;
  }

  getResponses(): Response[] {
    const responses: Response[] = [];
    this.miahoot=this.enseignantService.miahootAvecReponses;


    this.miahoot?.questions.forEach((question) => {
      const selectedResponse = question.reponses.find((r) => r.isSelected);
      if (selectedResponse) {
        const response: Response = {
          // label: question.label,
          label: selectedResponse.label,
          isSelected: selectedResponse.label.toString() === 'true',
          estValide: selectedResponse.estValide.toString() === 'true',
        };
        responses.push(response);
      }
    });
    return responses;
  }

  showResponsesClicked(): void {
    this.showResponses = true;
  }
}
