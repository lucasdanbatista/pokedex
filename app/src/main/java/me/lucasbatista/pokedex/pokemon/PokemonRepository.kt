package me.lucasbatista.pokedex.pokemon

import androidx.lifecycle.asLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

class PokemonRepository @Inject constructor(private val webService: PokemonWebService, private val dao: PokemonDao) {
    fun find(pokemonId: Int): Flow<Pokemon?> {
        refresh(pokemonId)
        return dao.find(pokemonId)
    }

    private fun refresh(pokemonId: Int) = CoroutineScope(IO).launch {
        val pokemon = webService.findPokemon(pokemonId)
        val cache = dao.find(pokemon.id).asLiveData()
        if (cache.value != null) dao.update(pokemon) else dao.insert(pokemon)
    }
}
