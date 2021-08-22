package me.lucasbatista.pokedex

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor() : ViewModel() {
    private val model = Pokemon(1, "Pikachu") //TODO: Remove mock
    val name = MutableLiveData(model.name)
}
