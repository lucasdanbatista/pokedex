package me.lucasbatista.pokedex

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor() : ViewModel() {
    val pokemon: LiveData<Pokemon> = MutableLiveData(Pokemon(1, "Pikachu"))
}
