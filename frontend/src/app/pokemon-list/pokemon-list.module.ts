import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PokemonListComponent } from './pokemon-list/pokemon-list.component';
import {PokemonListRoutingModule} from "./pokemon-list-routing.module";
import {MatChipsModule, MatTableModule} from "@angular/material";
import {GetPokemonService} from "./services/get-pokemon.service";
import {HttpModule} from "@angular/http";
import {HttpClientModule} from "@angular/common/http";

@NgModule({
  imports: [
    CommonModule,
    HttpClientModule,
    MatTableModule,
    MatChipsModule,
    PokemonListRoutingModule
  ],
  declarations: [PokemonListComponent],
  providers: [GetPokemonService]
})
export class PokemonListModule { }
