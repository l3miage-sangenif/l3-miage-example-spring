import { Component } from '@angular/core';

@Component({
  selector: 'app-qcm',
  templateUrl: './qcm.component.html',
  styleUrls: ['./qcm.component.css'],
})
export class QCMComponent {
  b: boolean = false;
  ok: boolean = false;
  qcm: any;
  public choice() {
    //le clique sur le button create a new Miahoot met le booleen b à true
    if (this.b == true) {
      this.b = false;
    } else {
      this.b = true;
    }
  }
  getOptionQcm() {
    const affichageElement = document.getElementById('affichage');
    if (affichageElement !== null) {
      affichageElement.textContent = this.qcm;
    } else {
      console.error("L'élément avec l'ID 'affichage' n'a pas été trouvé");
    }
  }

  //N'a pas l'air de marcher
  public fermer() {
    if (this.qcm !== null) {
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
