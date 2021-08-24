package me.lucasbatista.pokedex.module

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.lucasbatista.pokedex.persistence.PokedexDatabase
import me.lucasbatista.pokedex.persistence.PokemonDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Singleton
    @Provides
    fun providesDatabase(app: Application) =
        Room.databaseBuilder(app, PokedexDatabase::class.java, "pokedex_db").build()

    @Provides
    fun providesPokemonDao(database: PokedexDatabase): PokemonDao = database.pokemonDao()
}
