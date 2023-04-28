import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AccueilComponent } from './accueil/accueil.component';
import { EnseignantComponent } from './enseignant/enseignant.component';
import { ListeDeQuestionComponent } from './liste-de-question/liste-de-question.component';
import { ResultatQCMComponent } from './resultat-qcm/resultat-qcm.component';

const routes: Routes = [
  { path: '', component: AccueilComponent },
  { path: 'accueil', component: AccueilComponent },
  { path: 'enseignant', component: EnseignantComponent },
  { path: 'participant', component: ListeDeQuestionComponent },
  { path: 'resultat', component: ResultatQCMComponent },
  { path: '**',   redirectTo: '/accueil', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
