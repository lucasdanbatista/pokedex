package me.lucasbatista.pokedex

import javax.inject.Inject

class PokemonRepository @Inject constructor(private val dao: PokemonDao) {
    fun find(id: Int) = dao.find(id)

    fun create(pokemon: Pokemon) = dao.create(pokemon)
}
