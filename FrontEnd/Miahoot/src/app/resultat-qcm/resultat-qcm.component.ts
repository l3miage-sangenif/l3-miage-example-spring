import { Component, Input } from '@angular/core';
import { MiahootService } from '../miahoot.service';
import {
  Miahoot,
  Question,
  Response,
  SelectedOption,
} from '../miahoot.service';

@Component({
  selector: 'app-resultat-qcm',
  templateUrl: './resultat-qcm.component.html',
  styleUrls: ['./resultat-qcm.component.css'],
})
export class ResultatQCMComponent {
  @Input() miahoot: Miahoot | undefined;
  showResponses = false;

  constructor(private miahootService: MiahootService) {}

  ngOnInit(): void {
    this.miahoot = this.miahootService.getMiahoot();
  }

  getScore(): number {
    const numResponses = this.miahoot?.questions.length || 0;
    let numCorrect = 0;
    this.miahoot?.questions.forEach((question) => {
      const selectedResponse = question.responses.find((r) => r.selected);
      if (selectedResponse && selectedResponse.estvalide) {
        numCorrect++;
      }
    });
    return (numCorrect / numResponses) * 100;
  }

  getResponses(): Response[] {
    const responses: Response[] = [];
    this.miahoot?.questions.forEach((question) => {
      const selectedResponse = question.responses.find((r) => r.selected);
      if (selectedResponse) {
        const response: Response = {
          // label: question.label,
          label: selectedResponse.label,
          selected: selectedResponse.label.toString() === 'true',
          estvalide: selectedResponse.estvalide.toString() === 'true',
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
