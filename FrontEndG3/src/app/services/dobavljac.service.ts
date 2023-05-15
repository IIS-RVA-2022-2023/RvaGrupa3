import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { DOBAVLJAC_URL } from '../constants';
import { Dobavljac } from '../models/dobavljac';

@Injectable({
  providedIn: 'root',
})
export class DobavljacService {
  constructor(private httpClient: HttpClient) {}

  public getAllDobavljacs(): Observable<any> {
    return this.httpClient.get(`${DOBAVLJAC_URL}`);
  }

  public addDobavljac(artikl: Dobavljac): Observable<any> {
    return this.httpClient.post(`${DOBAVLJAC_URL}`, artikl);
  }

  public updateDobavljac(artikl: Dobavljac): Observable<any> {
    return this.httpClient.put(`${DOBAVLJAC_URL}/${artikl.id}`, artikl);
  }

  public deleteDobavljac(id: number): Observable<any> {
    return this.httpClient.delete(`${DOBAVLJAC_URL}/${id}`);
  }
}
