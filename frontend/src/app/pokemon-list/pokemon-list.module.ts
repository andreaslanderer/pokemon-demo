import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {PokemonListComponent} from './pokemon-list/pokemon-list.component';
import {PokemonListRoutingModule} from './pokemon-list-routing.module';
import {MatChipsModule, MatTableModule} from '@angular/material';
import {HttpClientModule} from '@angular/common/http';
import {PokemonControllerService} from '../services/api/pokemonController.service';

@NgModule({
  imports: [
    CommonModule,
    HttpClientModule,
    MatTableModule,
    MatChipsModule,
    PokemonListRoutingModule
  ],
  declarations: [PokemonListComponent],
  providers: [PokemonControllerService]
})
export class PokemonListModule { }
