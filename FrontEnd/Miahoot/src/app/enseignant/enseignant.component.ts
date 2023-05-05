import { Component, OnInit } from '@angular/core';
import { EnseignantService } from '../services/enseignant.service';
import { Miahoot } from '../models/miahoot';
import { MiahootService } from '../miahoot.service';
import { Router } from '@angular/router';
import { PartageMiahootService } from '../partage-miahoot.service';
import { TransfertEnseignantQcmService } from '../transfert-enseignant-qcm.service';


@Component({
  selector: 'app-enseignant',
  templateUrl: './enseignant.component.html',
  styleUrls: ['./enseignant.component.css'],
})
export class EnseignantComponent implements OnInit {
  b: boolean = false;
  namemiahoot: any;
  miahoots: any;
  //Definition d'un tableau de Miahoot nommé miahoot en dure
  miahoot :Miahoot[] = [{
    nom: 'Kadi',
    questions: [
      {
        label: 'Dili',
        responses: [
          { label: 'Ami', estValide: true },
          { label: 'AB', estValide: false }
      ],
      },
      {
        label: 'Dila',
        responses: [
          { label: 'Ami', estValide: false },
          { label: 'CC', estValide: true}

      ],
      },
    ],
  }];

  tableauDesMiahoots : Miahoot[] = [];

  displayedColumns: string[] = ['No', 'nom',/*'question'*/ 'actions'];
  
  constructor(private enseignantService: EnseignantService,private router: Router, public miahootRecupere : PartageMiahootService, private transfertEnseignantQcmService:TransfertEnseignantQcmService) {}

  ngOnInit(): void {
    this.getAllMiahoots();
    console.log(this.miahoots);
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
  showAlert(){
      alert("Veuillez entrer un titre !");
  }

  /*Pour récupérer tous les miahoots déjà crées */
  getAllMiahoots(): Miahoot[]{
    /*this.enseignantService.getAllMiahoot('123').subscribe((data) => {
      this.tableauDesMiahoots = data;
    });*/
    this.enseignantService.getMiahootById(1).subscribe((data) => {
     this.tableauDesMiahoots.push(data);
    })
    return this.tableauDesMiahoots;
  }

  //cette fonction ouvrira une boite de dialogue ou on pourra editer unmiahoot
  onEdit(miahoot:Miahoot):void{
    //this.transfertEnseignantQcmService.miahoot = miahoot;
    //this.router.navigate(['/qcm']);
  }

  onDelete(miahoot:Miahoot):void{
    //this.enseignantService.deleteMiahoot(miahoot.id).subscribe();
  }
  onPresent(miahoot:Miahoot):void{
    this.miahootRecupere.miahoot = miahoot; 
    this.router.navigate(['/participant']);
  }
}
