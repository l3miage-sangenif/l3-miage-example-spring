import { Component, OnInit } from '@angular/core';
import { EnseignantService } from '../services/enseignant.service';

@Component({
  selector: 'app-enseignant',
  templateUrl: './enseignant.component.html',
  styleUrls: ['./enseignant.component.css'],
})
export class EnseignantComponent implements OnInit {
  name: string = 'DEMBELE Oumou';
  b: boolean = false;
  ok: boolean = false;
  namemiahoot: any;
  miahoots: any;
  //Definition d'un tableau de Miahoot nommé miahoot en dure
  miahoot = {
    nom: 'Kadi',
    owner: 1,
    question: [
      {
        label: 'Dili',
        response: [{ label: 'Ami', estvalide: true }],
      },
    ],
  };
  constructor(private enseignantService: EnseignantService) {}

  ngOnInit(): void {
    this.enseignantService.getAllMiahoot().subscribe((data) => {
      this.miahoots = data;
    });
  }

  public choice() {
    //le clique sur le button create a new Miahoot met le booleen b à true
    if (this.b == true) {
      this.b = false;
    } else {
      this.b = true;
    }
  }
  /*un mihoot pour le moment, plus tard faire un tableau de Miahoot*/
  getMiahootTitle() {
    const affichageElement = document.getElementById('affichage');
    if (affichageElement !== null) {
      affichageElement.textContent = this.namemiahoot;
    } else {
      console.error("L'élément avec l'ID 'affichage' n'a pas été trouvé");
    }
  }
  public fermer() {
    if (this.namemiahoot !== null) {
      if (this.ok == true) {
        this.ok = false;
      } else {
        this.ok = true;
      }
    } else {
      console.log('Veuillez entrer un titre!');
    }
  }
}
