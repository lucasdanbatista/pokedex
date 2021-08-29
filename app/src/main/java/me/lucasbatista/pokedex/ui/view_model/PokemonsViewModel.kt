package me.lucasbatista.pokedex.ui.view_model

import androidx.lifecycle.ViewModel
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import me.lucasbatista.pokedex.data.cache.PokemonDao
import me.lucasbatista.pokedex.data.network.PokemonRemoteMediator
import me.lucasbatista.pokedex.data.network.PokemonWebService
import javax.inject.Inject

@ExperimentalPagingApi
@HiltViewModel
class PokemonsViewModel @Inject constructor(webService: PokemonWebService, dao: PokemonDao) :
    ViewModel() {
    val pokemons = Pager(
        config = PagingConfig(100),
        remoteMediator = PokemonRemoteMediator(webService, dao),
        pagingSourceFactory = { dao.findAll() }
    ).flow
}