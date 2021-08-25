package me.lucasbatista.pokedex.network

import me.lucasbatista.pokedex.persistence.Pokemon
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonWebService {
    @GET("v0/pokemons/{id}")
    suspend fun findById(@Path("id") id: Int): Pokemon

    @GET("v0/pokemons")
    suspend fun findAll(): List<Pokemon>
}