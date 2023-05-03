import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { Miahoot } from '../miahoot.service';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class EnseignantService {
  
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
    }),
  };

  constructor(private http: HttpClient) {}
/*recuperation d'un miahoot*/
getMiahootById(miahootid: number): Observable<any> {
  const url = `${environment.baseUrl}/miahoots/${miahootid}`; // URL de la ressource à supprimer
  return this.http.get(url, this.httpOptions); // Effectue la requête DELETE
}
  getAllMiahoot(): Observable<any> {
    return this.http.get(environment.baseUrl+'/miahoots');
  }
  /*Création d'un miahoot*/
  creatMiahoot(miahoot: any): Observable<any> {
    return this.http.post(environment.baseUrl+'/miahoots', miahoot, this.httpOptions);
  }
  /*Suppression d'un miahoot*/
  deleteMiahoot(miahootid: number): Observable<any> {
    const url = `${environment.baseUrl}/miahoots/${miahootid}`; // URL de la ressource à supprimer
    return this.http.delete(url, this.httpOptions); // Effectue la requête DELETE
  }
  /*Mis à jour d'un miahoot*/
  updateMiahoot(miahootid: number, updatedData: any): Observable<any> {
    const url = `${environment.baseUrl}/miahoots/${miahootid}`; // URL de la ressource à mettre à jour
    return this.http.put(url, updatedData, this.httpOptions); // Effectue la requête PUT
  }
}
