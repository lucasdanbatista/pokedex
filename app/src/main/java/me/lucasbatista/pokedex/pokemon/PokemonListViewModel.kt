package me.lucasbatista.pokedex.pokemon

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(repository: PokemonRepository) : ViewModel() {
    val pokemons = repository.findAll().asLiveData()
}
