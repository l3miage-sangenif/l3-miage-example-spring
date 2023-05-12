import { Component, Input } from '@angular/core';

import { Miahoot } from '../models/miahoot';
import { Response } from '../models/response';
import { Question } from '../models/question';
import { EnseignantService } from '../services/enseignant.service';
import { ActivatedRoute } from '@angular/router';
import { PartageMiahootService } from '../partage-miahoot.service';

@Component({
  selector: 'app-resultat-qcm',
  templateUrl: './resultat-qcm.component.html',
  styleUrls: ['./resultat-qcm.component.css'],
})
export class ResultatQCMComponent {
  miahoot!: Miahoot;
  showResponses = false;
  idMiahoot!: number;

  constructor(private miahootService: EnseignantService, private route : ActivatedRoute, private envoiMiahoot : PartageMiahootService) {}

  ngOnInit(): void {
    /*this.miahootService.getMiahootById(Number(this.route.snapshot.paramMap.get('idMiahootR'))).subscribe(miahootRecupere=>{
      this.miahoot = miahootRecupere as Miahoot;

    });*/
    this.miahoot = this.envoiMiahoot.miahoot;
  }

  getScore(): number {
    const numResponses = this.miahoot.questions.length || 0;
    let numCorrect = 0;
    this.miahoot.questions.forEach((question) => {
      const selectedResponse = question.reponses.find((r) => r.selected);
      if (selectedResponse && selectedResponse.estValide) {
        numCorrect++;
      }
    });
    return (numCorrect / numResponses) * 100;
  }

  getResponses(): Response[] {
    const responses: Response[] = [];
    this.miahoot.questions.forEach((question) => {
      const selectedResponse = question.reponses.find((r) => r.selected);
      if (selectedResponse) {
        const response: Response = {
          // label: question.label,
          label: selectedResponse.label,
          selected: selectedResponse.label.toString() === 'true',
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
