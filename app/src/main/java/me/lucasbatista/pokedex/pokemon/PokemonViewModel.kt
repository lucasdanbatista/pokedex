package me.lucasbatista.pokedex.pokemon

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(private val repository: PokemonRepository) : ViewModel() {
    val pokemon = repository.find(24).asLiveData()
}
