package me.lucasbatista.pokedex.network

import me.lucasbatista.pokedex.entity.Pokemon
import retrofit2.http.GET
import retrofit2.http.Query

data class Page<T>(
    val items: List<T>,
    val previous: Int?,
    val next: Int?,
)

interface PokemonWebService {
    @GET("v0/pokemons")
    suspend fun fetch(@Query("page") page: Int?): Page<Pokemon>
}