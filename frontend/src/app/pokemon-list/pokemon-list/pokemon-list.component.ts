import {ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {Pokemon} from '../model/pokemon.model';
import {PokemonPreview} from '../model/PokemonPreview';
import {PokemonControllerService} from '../../services/api/pokemonController.service';

@Component({
  selector: 'app-pokemon-list',
  templateUrl: './pokemon-list.component.html',
  styleUrls: ['./pokemon-list.component.css']
})
export class PokemonListComponent implements OnInit {

  public pokemonList: Pokemon[] = [];
  public displayedColumns = ['id', 'name', 'type'];

  constructor(private service: PokemonControllerService,
              private changes: ChangeDetectorRef) {

  }

  ngOnInit() {
    this.service.getPokemonUsingGET()
      .subscribe((previewList: PokemonPreview[]) => {this.mapPreviewToPokemon(previewList)},
        this.handleError,
        () => { this.appendPokemon() });
  }

  public mapPreviewToPokemon(previewList: PokemonPreview[]) {
    const newPokemonList = previewList.map((preview: PokemonPreview) => {
      const pm = new Pokemon();
      pm.name = preview.name;
      pm.id = preview.id;
      return pm;
    });
    this.pokemonList = newPokemonList;
    console.log(this.pokemonList);
  }

  public appendPokemon() {
  this.pokemonList.forEach((pokemon: Pokemon) => {
    this.service.getPokemonForIdUsingGET(pokemon.id)
      .subscribe((newPokemon: Pokemon) => {
        pokemon.types = newPokemon.type.split(' ');
      }, this.handleError);
    });
  }

  public handleError(err: any) {
    console.info(err);
  }
}
