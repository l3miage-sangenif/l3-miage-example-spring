import { Component, OnInit } from '@angular/core';
import { Miahoot } from '../models/miahoot';
import { Question } from '../models/question';
import { Response } from '../models/response';
import { ActivatedRoute } from '@angular/router';
import { EnseignantService } from '../services/enseignant.service';
import { TransfertEnseignantQcmService } from '../transfert-enseignant-qcm.service';

@Component({
  selector: 'app-qcm',
  templateUrl: './qcm.component.html',
  styleUrls: ['./qcm.component.css'],
})
export class QCMComponent implements OnInit {
  b: boolean = false;
  qrcode: boolean = false;
  lien: boolean = false;
  qcm:any
  chaine: string =
    'https://api.qrserver.com/v1/create-qr-code/?data=HelloWorld&amp';
  //ajouté par damessis pour qu'on ai au minimum deux reponse par question
  /*questions: Question[] = [
    {
      label: '',
      responses: [{ label: '', estValide: false }, { label: '', estValide: false }],
    },
  ];*/
  titreMiahoot = '';

  miahoot :Miahoot= {
    nom: '',
    questions: [
      {
        label: '',
        responses: [{ label: '', estValide: false }, { label: '', estValide: false }],
      }
    ]
  }
  

  constructor(private serviceE : EnseignantService, private route : ActivatedRoute,private transfertEnseignantQcmService:TransfertEnseignantQcmService){

  }
  ngOnInit(): void {
    //console.log(this.route.snapshot.paramMap.get('name'));
    this.miahoot.nom = this.route.snapshot.paramMap.get('name') as string;
    //this.miahoot = this.transfertEnseignantQcmService.miahoot;


  }

  public choice() {
    //le clique sur le button create a new Miahoot met le booleen b à true
    if (this.b == true) {
      this.b = false;
    } else {
      this.b = true;
    }
  }
  
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
    this.miahoot.questions.push({
      label: '',
      responses: [{ label: '', estValide: false }, { label: '', estValide: false }],
    });
  }

  addResponse(questionIndex: number) {
    this.miahoot.questions[questionIndex].responses.push({ label: '', estValide: false });
  }

  removeResponse(questionIndex: number,index: number) {
    this.miahoot.questions[questionIndex].responses.splice(index, 1);
  }
  removeQuestion(index: number) {
    this.miahoot.questions.splice(index, 1);
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
    const m=this.miahoot;
    console.log({m});
    this.serviceE.createMiahoot(this.miahoot).subscribe(response => {
      console.log({response});
    });
  }
}
