package me.lucasbatista.pokedex.persistence

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import me.lucasbatista.pokedex.network.PokemonWebService
import javax.inject.Inject

class PokemonRepository @Inject constructor(
    private val webService: PokemonWebService,
    private val dao: PokemonDao
) {
    fun findAll(): Flow<List<Pokemon>> {
        CoroutineScope(IO).launch {
            val data = webService.findAll(100)
            for ((i) in data.results.withIndex()) {
                save(i + 1)
            }
        }
        return dao.findAll()
    }

    fun find(id: Int): Flow<Pokemon> {
        save(id)
        return dao.find(id)
    }

    private fun save(id: Int) = CoroutineScope(IO).launch {
        val pokemon = webService.find(id)
        if (dao.exists(pokemon.id)) dao.update(pokemon) else dao.insert(pokemon)
    }
}
