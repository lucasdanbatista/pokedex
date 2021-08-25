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
            if (dao.exists(pokemon.id)) dao.update(pokemon) else dao.insert(pokemon)
        }
        return dao.findById(id)
    }
}
