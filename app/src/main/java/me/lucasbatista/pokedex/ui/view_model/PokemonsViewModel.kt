package me.lucasbatista.pokedex.ui.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import me.lucasbatista.pokedex.repository.PokemonRepository
import javax.inject.Inject

@HiltViewModel
class PokemonsViewModel @Inject constructor(repository: PokemonRepository) : ViewModel() {
    val pokemons = repository.findAll().asLiveData()
}