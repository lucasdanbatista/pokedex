package me.lucasbatista.pokedex

import androidx.room.Database
import androidx.room.RoomDatabase
import me.lucasbatista.pokedex.pokemon.Pokemon
import me.lucasbatista.pokedex.pokemon.PokemonDao

@Database(entities = [Pokemon::class], version = 1)
abstract class PokedexDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
}
