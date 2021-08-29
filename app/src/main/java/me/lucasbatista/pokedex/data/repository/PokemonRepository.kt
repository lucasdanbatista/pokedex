package me.lucasbatista.pokedex.data.repository

import me.lucasbatista.pokedex.data.cache.PokemonDao
import me.lucasbatista.pokedex.data.entity.Pokemon
import me.lucasbatista.pokedex.data.network.Page
import me.lucasbatista.pokedex.data.network.PokemonWebService
import javax.inject.Inject

class PokemonRepository @Inject constructor(private val dao: PokemonDao, private val webService: PokemonWebService) {
    suspend fun fetch(page: Int?): Page<Pokemon> {
        return webService.fetch(page)
    }
}