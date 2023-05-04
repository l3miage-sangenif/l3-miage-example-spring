import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { Miahoot } from '../miahoot.service';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class EnseignantService {

  baseUrl = 'http://localhost:8080/api';
  
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
  // exemple de méthode POST pour créer un miahoot
  createMiahoot(miahoot: any): Observable<any> {
    const url = `${this.baseUrl}/miahoots/`; // endpoint pour créer un miahoot
    return this.http.post(url, miahoot);
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
