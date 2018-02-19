import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Pokemon} from "../model/pokemon.model";

@Injectable()
export class GetPokemonService {

  constructor(private http: HttpClient) { }

  getPokemon() {
    return this.http.get('http://localhost:8080/v01/pokemon');
  }

  getPokemonDetails(id: number) {
    return this.http.get(`http://localhost:8080/v01/pokemon/${id}`);
  }
}
