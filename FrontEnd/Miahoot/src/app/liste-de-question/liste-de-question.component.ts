import { Component, EventEmitter, Output } from '@angular/core';

import { Router } from '@angular/router';

interface Question {
  text: string;
  options: string[];
  answer: string;
  selectedOption?: string;
}

@Component({
  selector: 'app-liste-de-question',
  templateUrl: './liste-de-question.component.html',
  styleUrls: ['./liste-de-question.component.css'],
})
export class ListeDeQuestionComponent {
  /**************** Les attributs ***********************/
  public Result: boolean[] | undefined;
  @Output() answerSelected = new EventEmitter<void>();

  /**********  declaration d'une liste de question ***************/
  public questions: Question[] = [
    {
      text: 'Quel est le plus grand pays du monde ?',
      options: ['Russie', 'Canada', 'Chine', 'États-Unis'],
      answer: 'Russie',
      selectedOption: undefined,
    },
    {
      text: 'Quel est le plus petit pays du monde ?',
      options: ['Vatican', 'Nauru', 'Tuvalu', 'Monaco'],
      answer: 'Vatican',
      selectedOption: undefined,
    },
    {
      text: 'Quelle est la capitale du Canada ?',
      options: ['Toronto', 'Vancouver', 'Ottawa', 'Montréal'],
      answer: 'Ottawa',
      selectedOption: undefined,
    },
  ];

  /*********   Partie constructor *************************/
  // constructor(private router: Router) {}

  /****************** Les Methodes **************************/
  onAnswerSelected() {
    this.answerSelected.emit();
  }

  checkAnswers() {
    let correctAnswers = 0;
    this.questions.forEach((question) => {
      const selectedOption = (<HTMLInputElement>(
        document.querySelector(`input[name='${question.text}']:checked`)
      )).value;
      if (selectedOption === question.answer) {
        correctAnswers++;
      }
    });
    alert(
      `Vous avez répondu correctement à ${correctAnswers} questions sur ${this.questions.length}.`
    );
  }

  onQuestionClick(question: Question) {
    // Traitez la réponse de l'utilisateur à la question ici
    this.questions.forEach((element) => {
      if (question.answer == element.answer) {
        this.Result?.push(true);
      } else {
        this.Result?.push(false);
      }
    });
  }
}
