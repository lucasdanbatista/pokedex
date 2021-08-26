package me.lucasbatista.pokedex.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.lucasbatista.pokedex.network.PokemonWebService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Singleton
    @Provides
    fun providesHttpClient(): Retrofit = Retrofit.Builder()
        .baseUrl("http://192.168.0.3:8080") // TODO: replace local backend
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    fun providesPokemonWebService(retrofit: Retrofit): PokemonWebService =
        retrofit.create(PokemonWebService::class.java)
}
