package me.lucasbatista.pokedex.pokemon

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

class PokemonRepository @Inject constructor(private val webService: PokemonWebService, private val dao: PokemonDao) {
    fun find(id: Int): Flow<Pokemon> {
        refresh(id)
        return dao.find(id)
    }

    private fun refresh(id: Int) = CoroutineScope(IO).launch {
        val pokemon = webService.findPokemon(id)
        if (dao.exists(pokemon.id)) dao.update(pokemon) else dao.insert(pokemon)
    }
}
