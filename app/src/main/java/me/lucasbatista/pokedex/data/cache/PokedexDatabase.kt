package me.lucasbatista.pokedex.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import me.lucasbatista.pokedex.data.entity.Pokemon

@Database(entities = [Pokemon::class], version = 1)
@TypeConverters(StatisticListConverter::class)
abstract class PokedexDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
}
