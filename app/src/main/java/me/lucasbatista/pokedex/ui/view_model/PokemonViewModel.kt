package me.lucasbatista.pokedex.ui.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import me.lucasbatista.pokedex.persistence.PokemonRepository
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(repository: PokemonRepository) : ViewModel() {
    val pokemon = repository.find(26).asLiveData()
}
