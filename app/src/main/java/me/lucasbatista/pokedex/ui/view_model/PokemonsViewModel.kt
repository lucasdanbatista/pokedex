package me.lucasbatista.pokedex.ui.view_model

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import me.lucasbatista.pokedex.network.PokemonWebService
import me.lucasbatista.pokedex.persistence.PokemonDao
import javax.inject.Inject

@HiltViewModel
class PokemonsViewModel @Inject constructor(private val webService: PokemonWebService, private val dao: PokemonDao) :
    ViewModel() {
    val pokemons = Pager(
        config = PagingConfig(12),
        pagingSourceFactory = dao.findAll().asPagingSourceFactory()
    ).flow

    fun fetchData() = CoroutineScope(Dispatchers.IO).launch {
        webService.findAll().forEach { if (dao.exists(it.id)) dao.update(it) else dao.insert(it) }
    }
}