import { Porudzbina } from './../models/porudzbina';
import { PORUDZBINA_URL } from './../constants';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class PorudzbinaService {

  constructor(private httpClient:HttpClient) { }

  public getAllPorudzbinas():Observable<any>{
    return this.httpClient.get(`${PORUDZBINA_URL}`);
  }

  public addPorudzbina(porudzbina:Porudzbina):Observable<any>{
    return this.httpClient.post(`${PORUDZBINA_URL}`,porudzbina);
  }

  public updatePorudzbina(porudzbina:Porudzbina):Observable<any>{
    return this.httpClient.post(`${PORUDZBINA_URL}/${porudzbina.id}`,porudzbina);
  }

  public deletePorudzbina(id:number):Observable<any>{
    return this.httpClient.delete(`${PORUDZBINA_URL}/${id}`);
  }
}
