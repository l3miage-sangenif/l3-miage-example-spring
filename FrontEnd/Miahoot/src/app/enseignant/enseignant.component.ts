import { Component } from '@angular/core';

@Component({
  selector: 'app-enseignant',
  templateUrl: './enseignant.component.html',
  styleUrls: ['./enseignant.component.css'],
})
export class EnseignantComponent {
  name: string = 'DEMBELE Oumou';
  b: boolean = false;
  ok: boolean = false;
  namemiahoot: any;
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
