package me.lucasbatista.pokedex.pokemon

data class PokemonResult(
    val results: List<Result>
)

data class Result(
    val name: String
)
