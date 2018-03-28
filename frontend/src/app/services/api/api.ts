export * from './authController.service';
import { AuthControllerService } from './authController.service';
export * from './pokemonController.service';
import { PokemonControllerService } from './pokemonController.service';
export const APIS = [AuthControllerService, PokemonControllerService];
