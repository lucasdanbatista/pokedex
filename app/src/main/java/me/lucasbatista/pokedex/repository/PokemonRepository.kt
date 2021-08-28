package me.lucasbatista.pokedex.repository

import me.lucasbatista.pokedex.entity.Pokemon
import me.lucasbatista.pokedex.network.Page
import me.lucasbatista.pokedex.network.PokemonWebService
import me.lucasbatista.pokedex.persistence.PokemonDao
import javax.inject.Inject

class PokemonRepository @Inject constructor(private val dao: PokemonDao, private val webService: PokemonWebService) {
    suspend fun fetch(page: Int?): Page<Pokemon> {
        return webService.fetch(page)
    }
}