import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { PORUDZBINA_URL } from '../constants';
import { Porudzbina } from '../models/porudzbina';

@Injectable({
  providedIn: 'root'
})
export class PorudzbinaService {

  constructor(private httpClient:HttpClient) { }

  public getAllPorudzbina():Observable<any>{
    return this.httpClient.get(`${PORUDZBINA_URL}`);
  }

  public addPorudzbina(porudzbina:Porudzbina):Observable<any>{
    return this.httpClient.post(`${PORUDZBINA_URL}`,porudzbina);
  }

  public updatePorudzbina(porudzbina:Porudzbina):Observable<any>{
    return this.httpClient.put(`${PORUDZBINA_URL}/${porudzbina.id}`,porudzbina);
  }

  public deletePorudzbina(id:number):Observable<any>{
    return this.httpClient.delete(`${PORUDZBINA_URL}/${id}`);
  }
}
