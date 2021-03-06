package me.lucasbatista.pokedex.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.lucasbatista.pokedex.data.network.PokemonWebService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Singleton
    @Provides
    fun providesHttpClient(): Retrofit = Retrofit.Builder()
        .baseUrl("https://pokeapi.lucasbatista.me")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    fun providesPokemonWebService(retrofit: Retrofit): PokemonWebService =
        retrofit.create(PokemonWebService::class.java)
}
