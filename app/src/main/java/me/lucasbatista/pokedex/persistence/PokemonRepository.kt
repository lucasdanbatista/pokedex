package me.lucasbatista.pokedex.persistence

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import me.lucasbatista.pokedex.network.PokemonWebService
import javax.inject.Inject

class PokemonRepository @Inject constructor(private val webService: PokemonWebService, private val dao: PokemonDao) {
    fun findById(id: Int): Flow<Pokemon> {
        CoroutineScope(IO).launch {
            val pokemon = webService.findById(id)
            save(pokemon)
        }
        return dao.findById(id)
    }

    fun findAll(): Flow<List<Pokemon>> {
        CoroutineScope(IO).launch {
            val pokemons = webService.findAll()
            pokemons.forEach { save(it) }
        }
        return dao.findAll()
    }

    private fun save(pokemon: Pokemon) {
        if (dao.exists(pokemon.id)) dao.update(pokemon) else dao.insert(pokemon)
    }
}
