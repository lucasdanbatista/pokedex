package me.lucasbatista.pokedex.network

import me.lucasbatista.pokedex.persistence.Pokemon
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonWebService {
    @GET("v2/pokemon/{id}")
    suspend fun find(@Path("id") id: Int): Pokemon

    @GET("v2/pokemon")
    suspend fun findAll(@Query("limit") limit: Int): PokemonResult
}

data class PokemonResult(
    val results: List<Result>
)

data class Result(
    val name: String
)
