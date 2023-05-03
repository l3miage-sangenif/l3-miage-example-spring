import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { Miahoot } from '../miahoot.service';

@Injectable({
  providedIn: 'root',
})
export class EnseignantService {
  baseUrl = 'http://localhost:8080/api/miahoots/1';
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
    }),
  };

  constructor(private http: HttpClient) {}

  getAllMiahoot(): Observable<any> {
    return this.http.get(this.baseUrl);
  }
  /*Création d'un miahoot*/
  creatMiahoot(miahoot: any): Observable<any> {
    return this.http.post(this.baseUrl, miahoot, this.httpOptions);
  }
  /*Suppression d'un miahoot*/
  deleteMiahoot(miahootid: number): Observable<any> {
    const url = `${this.baseUrl}/${miahootid}`; // URL de la ressource à supprimer
    return this.http.delete(url, this.httpOptions); // Effectue la requête DELETE
  }
  /*Mis à jour d'un miahoot*/
  updateMiahoot(miahootid: number, updatedData: any): Observable<any> {
    const url = `${this.baseUrl}/${miahootid}`; // URL de la ressource à mettre à jour
    return this.http.put(url, updatedData, this.httpOptions); // Effectue la requête PUT
  }
}
