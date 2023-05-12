import { Component, OnInit } from '@angular/core';
import { EnseignantService } from '../services/enseignant.service';
import { Miahoot } from '../models/miahoot';

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
  userUid!: string;
  //Definition d'un tableau de Miahoot nommé miahoot en dure
  miahoot:Miahoot[]=[];

  tableauDesMiahoots : Miahoot[] = [];

  displayedColumns: string[] = ['No', 'nom',/*'question'*/ 'actions'];

  constructor(private enseignantService: EnseignantService,private router: Router, public miahootRecupere : PartageMiahootService, private transfertEnseignantQcmService:TransfertEnseignantQcmService) {
    if(transfertEnseignantQcmService.idUser!=undefined)
    this.setUserUid(transfertEnseignantQcmService.idUser);
  }

  ngOnInit(): void {
    this.userUid=this.getUserUid();

    this.getAllMiahoots();
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
    this.enseignantService.getAllMiahoot(this.userUid).subscribe((data) => {
      this.tableauDesMiahoots = data;
      console.log(data);
      console.log(this.userUid);
    });
    return this.tableauDesMiahoots;
  }

  //cette fonction ouvrira une boite de dialogue ou on pourra editer unmiahoot
  onEdit(miahoot:Miahoot):void{
    this.enseignantService.updateMiahoot(miahoot.miahootId, miahoot).subscribe();
    this.router.navigate(['/qcm/:name']);
  }

  onDelete(miahoot:Miahoot):void{
    this.enseignantService.deleteMiahoot(miahoot.miahootId).subscribe();
  }
  onPresent(miahoot:Miahoot):void{
    this.miahootRecupere.miahoot = miahoot;
    console.log(this.miahootRecupere.miahoot);
    this.router.navigate(['/participant/'+miahoot.miahootId]);
  }

  setUserUid(data:any){
    localStorage.setItem("userUid",JSON.stringify(data));
  }

  getUserUid(){
    let data = localStorage.getItem("userUid");
    if(data!=null)
    return JSON.parse(data);
  }


}
