import { Component, OnInit } from '@angular/core';
import { EnseignantService } from '../services/enseignant.service';
import { Miahoot } from '../models/miahoot';
import { MiahootService } from '../miahoot.service';
import { Router } from '@angular/router';


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
  miahoot :any[] = [{
    nom: 'Kadi',
    owner: 1,
    question: [
      {
        label: 'Dili',
        response: [{ label: 'Ami', estvalide: true }],
      },
    ],
  }];

  tableauDesMiahoots : Miahoot[] = [];

  displayedColumns: string[] = ['No', 'nom', 'actions'];
  
  constructor(private enseignantService: EnseignantService,private router: Router) {}

  ngOnInit(): void {
    /*this.enseignantService.getAllMiahoot().subscribe((data) => {
      this.miahoots = data;
      console.log(this.miahoots);
    });*/
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
  createMiahoot(miahoot : Miahoot){
    this.enseignantService.createMiahoot(miahoot);
  }

  /*Pour récupérer tous les miahoots déjà crées */
  getAllMiahoots(): Miahoot[]{
    this.enseignantService.getAllMiahoot().subscribe((data) => {
      this.tableauDesMiahoots = data;
    });
    
    return this.tableauDesMiahoots;
  }

  //cette fonction ouvrira une boite de dialogue ou on pourra editer unmiahoot
  onEdit(miahoot:Miahoot):void{
    //this.router.navigate(['/qcm/'+miahoot.nom, miahoot]);
  }

  onDelete(miahoot:Miahoot):void{
    //this.enseignantService.deleteMiahoot(miahoot.id).subscribe();
  }
  onPresent(miahoot:Miahoot):void{

  }
}
