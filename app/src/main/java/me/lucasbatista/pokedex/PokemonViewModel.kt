package me.lucasbatista.pokedex

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(private val repository: PokemonRepository) : ViewModel() {
    val pokemon = repository.find(1).asLiveData()

    //TODO: Remove mock
    fun create() = CoroutineScope(IO).launch {
        repository.create(Pokemon(1, "Pikachu"))
    }
}
