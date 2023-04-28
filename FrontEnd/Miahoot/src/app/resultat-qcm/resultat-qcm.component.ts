import { Component, Input } from '@angular/core';
interface Question {
  text: string;
  options: string[];
  answer: string;
  selectedOption: string;
}

@Component({
  selector: 'app-resultat-qcm',
  templateUrl: './resultat-qcm.component.html',
  styleUrls: ['./resultat-qcm.component.css'],
})
export class ResultatQCMComponent {
  @Input() questions: Question[] | undefined;
}
