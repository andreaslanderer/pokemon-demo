import { TestBed, inject } from '@angular/core/testing';

import { GetPokemonService } from './get-pokemon.service';

describe('GetPokemonService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [GetPokemonService]
    });
  });

  it('should be created', inject([GetPokemonService], (service: GetPokemonService) => {
    expect(service).toBeTruthy();
  }));
});
