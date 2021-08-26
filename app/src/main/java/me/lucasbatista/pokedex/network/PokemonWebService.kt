package me.lucasbatista.pokedex.network

import me.lucasbatista.pokedex.entity.Pokemon
import retrofit2.http.GET

interface PokemonWebService {
    @GET("v0/pokemons")
    suspend fun findAll(): List<Pokemon>
}