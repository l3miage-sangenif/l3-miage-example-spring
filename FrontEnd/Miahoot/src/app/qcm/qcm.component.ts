import { Component } from '@angular/core';


interface Response {
  label: string;
  estValide: boolean;
}

interface Question {
  label: string;
  responses: Response[];
}

interface Miahoot {
  nom: string;
  questions: Question[];
}

@Component({
  selector: 'app-qcm',
  templateUrl: './qcm.component.html',
  styleUrls: ['./qcm.component.css'],
})
export class QCMComponent {
  b: boolean = false;
  qrcode: boolean = false;
  lien: boolean = false;
  qcm:any
  chaine: string =
    'https://api.qrserver.com/v1/create-qr-code/?data=HelloWorld&amp';

  //ajouté par damessis
  questions: Question[] = [
    {
      label: '',
      responses: [{ label: '', estValide: false }, { label: '', estValide: false }],
    },
  ];

  public choice() {
    //le clique sur le button create a new Miahoot met le booleen b à true
    if (this.b == true) {
      this.b = false;
    } else {
      this.b = true;
    }
  }
  /*A Implementé
  getLienQrcode() {
    const affichageElement = document.getElementById('affichage');
    if (affichageElement !== null) {
      affichageElement.textContent = this.qcm;
    } else {
      console.error("L'élément avec l'ID 'affichage' n'a pas été trouvé");
    }
  }*/
  public qr_code() {
    if (this.qrcode === true) {
      this.qrcode = false;
    } else {
      this.qrcode = true;
    }
  }
  public entrer_lien() {
    if (this.lien === true) {
      this.lien = false;
    } else {
      this.lien = true;
    }
  }

  //ajouté par damessis
  addQuestion() {
    this.questions.push({
      label: '',
      responses: [{ label: '', estValide: false }, { label: '', estValide: false }],
    });
  }

  addResponse(questionIndex: number) {
    this.questions[questionIndex].responses.push({ label: '', estValide: false });
  }

  removeResponse(questionIndex: number,index: number) {
    this.questions[questionIndex].responses.splice(index, 1);
  }
  removeQuestion(index: number) {
    this.questions.splice(index, 1);
  }

  getValidResponses(question:Question) {
    return question.responses.filter(response => response.estValide);
  }

  onToggleClick(response: any, question: any) {
    const validResponses = this.getValidResponses(question);
    if (validResponses.length >= question.responses.length) {
      response.estValide = false;
      alert('Le nombre de réponses valides doit être inférieur au nombre total d\'options');
    }
  }
  createMiahoot(){
  }
}
