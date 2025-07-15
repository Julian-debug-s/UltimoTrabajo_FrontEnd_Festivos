import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { FestivosDTO } from '../../shared/entidades/FestivosDTO';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FestivosService {

  private url:string;

  constructor(private http: HttpClient) { 
    this.url = `${environment.urlBase}/festivos`;
  }

  public verificar(fecha: Date):Observable<boolean>{
    const anio = fecha.getFullYear();
    const mes = (fecha.getMonth() + 1).toString().padStart(2, '0');
    const dia = fecha.getDate().toString().padStart(2, '0');
    return this.http.get<boolean>(`${this.url}/verificar/${anio}/${mes}/${dia}`);
  }

  public listar(anio: number):Observable<FestivosDTO[]>{
    return this.http.get<FestivosDTO[]>(`${this.url}/listar/${anio}`);
  }

}
