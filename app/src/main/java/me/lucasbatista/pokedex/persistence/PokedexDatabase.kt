package me.lucasbatista.pokedex.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import me.lucasbatista.pokedex.entity.Pokemon

@Database(entities = [Pokemon::class], version = 1)
@TypeConverters(StatisticListConverter::class)
abstract class PokedexDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
}
