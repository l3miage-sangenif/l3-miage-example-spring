import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of, throwError } from 'rxjs';
import { catchError, retry, tap } from 'rxjs/operators';

export interface Response {
  label: String;
  estvalide: Boolean;
  selected: Boolean;
}

export interface Question {
  label: String;
  responses: Response[];
}

export interface Miahoot {
  nom: String;
  owner: Number;
  questions: Question[];
}
export interface SelectedOption {
  [key: string]: string;
}
@Injectable({
  providedIn: 'root',
})
export class MiahootService {
  public miahoot: Miahoot = {
    nom: 'Mon miahoot',
    owner: 12345,
    questions: [
      {
        label: 'Quelle est la capitale de la France ?',
        responses: [
          {
            label: 'Paris',
            estvalide: true,
            selected: false,
          },
          {
            label: 'Lyon',
            estvalide: false,
            selected: false,
          },
          {
            label: 'Marseille',
            estvalide: false,
            selected: false,
          },
          {
            label: 'Nice',
            estvalide: false,
            selected: false,
          },
        ],
      },
      {
        label: 'Qui est l\'auteur du roman "Les Misérables" ?',
        responses: [
          {
            label: 'Gustave Flaubert',
            estvalide: false,
            selected: false,
          },
          {
            label: 'Emile Zola',
            estvalide: false,
            selected: false,
          },
          {
            label: 'Victor Hugo',
            estvalide: true,
            selected: false,
          },
          {
            label: 'Marcel Proust',
            estvalide: false,
            selected: false,
          },
        ],
      },
    ],
  };
  public getMiahoot() {
    return this.miahoot;
  }
  constructor(private http: HttpClient) {}
  showResults: boolean = false;

  private miahootUrl = 'api/v1/'; // URL to web api

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
  };

  /** POST: add a new miahoot to the server */
  // addmiahoot(miahoot: Miahoot): Observable<Miahoot> {
  //   return this.http.post<Miahoot>(this.miahootUrl, miahoot, this.httpOptions).pipe(
  //     tap((newmiahoot: Miahoot) => this.log(`added miahoot w/ id=${newmiahoot.owner}`)),
  //     catchError(this.handleError<Miahoot>('addmiahoot'))
  //   );
  // }
}
