import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { environment } from '../../../enviroments/enviroment';
import { Videogame } from './videogames';

@Injectable({
  providedIn: 'root',
})
export class VideogameService {

  constructor() {}

  http = inject(HttpClient);

  getAllVideogames() {
    return this.http.get<any>(environment.urlApi + '/videogames');
  }

  getVideogame(id: number) {
    return this.http.get<any>(environment.urlApi + '/videogames/' + id);
  }

  addVideogame(videogame: Videogame) {
    return this.http.post<any>(environment.urlApi + '/videogames', videogame);
  }

  deleteVideogame(id: number) {
    return this.http.delete<any>(environment.urlApi + '/videogames/' + id);
  }

  updateVideogame(id: number, videogame: Videogame) {
    return this.http.put<any>(environment.urlApi + '/videogames/' + id, videogame);
  }
}
