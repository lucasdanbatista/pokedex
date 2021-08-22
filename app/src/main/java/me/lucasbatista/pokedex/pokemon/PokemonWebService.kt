package me.lucasbatista.pokedex.pokemon

import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonWebService {
    @GET("v2/pokemon/{id}")
    suspend fun findPokemon(@Path("id") id: Int): Pokemon
}
