package me.lucasbatista.pokedex.pokemon

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonWebService {
    @GET("v2/pokemon/{id}")
    suspend fun find(@Path("id") id: Int): Pokemon

    @GET("v2/pokemon")
    suspend fun findAll(@Query("limit") limit: Int): PokemonResult
}
