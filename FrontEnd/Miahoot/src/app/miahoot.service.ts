import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, retry, tap } from 'rxjs/operators';


interface Response{
  label : String;
  estvalide: Boolean;
}

interface Question{
  label : String;
  responses : Response[];
}

interface Miahoot{
  nom : String;
  owner : Long;
  questios : Question[];
}


@Injectable({
  providedIn: 'root'
})
export class MiahootService {

  private miahootUrl = 'api/v1/';  // URL to web api

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };
  

  constructor(private http: HttpClient) { }
   
  /** POST: add a new miahoot to the server */
addmiahoot(miahoot: Miahoot): Observable<Miahoot> {
  return this.http.post<Miahoot>(this.miahootUrl, miahoot, this.httpOptions).pipe(
    tap((newmiahoot: Miahoot) => this.log(`added miahoot w/ id=${newmiahoot.owner}`)),
    catchError(this.handleError<Miahoot>('addmiahoot'))
  );
}

  
}
