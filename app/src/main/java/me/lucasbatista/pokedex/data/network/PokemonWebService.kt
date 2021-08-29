package me.lucasbatista.pokedex.data.network

import me.lucasbatista.pokedex.data.entity.Pokemon
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

data class Page<T>(
    val items: List<T>,
    val previous: Int?,
    val next: Int?,
)

interface PokemonWebService {
    @GET("v0/pokemons/{id}")
    suspend fun findById(@Path("id") id: Int): Response<Page<Pokemon>>

    @GET("v0/pokemons")
    suspend fun findByPage(@Query("page") page: Int? = null): Response<Page<Pokemon>>
}