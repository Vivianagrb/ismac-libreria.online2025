import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Categoria } from '../model/categoria.model';

@Injectable({
  providedIn: 'root'
})
export class CategoriaService {
private baseURL = "http://localhost:8080/api/categoria";

  constructor(private http: HttpClient){  }
  
  findAll(): Observable <Categoria[]>{
    return this.http.get<Categoria[]>(this.baseURL);
  } 
findOne(id:number): Observable <Categoria[]>{
    return this.http.get<Categoria[]>(`${this.baseURL}/${id}`);
  } 

save(categoria:Categoria): Observable <Categoria>{
    return this.http.post<Categoria>( this.baseURL, categoria);
  }  

update(id:number, categoria:Categoria): Observable <Categoria>{
    return this.http.put<Categoria>( `${this.baseURL}/${id}`, categoria);
  }  
delete(id:number):  Observable <void>{
    return this.http.delete<void>(`${this.baseURL}/${id}`);
  } 


}
