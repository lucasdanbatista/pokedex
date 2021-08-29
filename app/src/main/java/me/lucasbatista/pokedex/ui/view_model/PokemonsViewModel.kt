package me.lucasbatista.pokedex.ui.view_model

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import me.lucasbatista.pokedex.data.paging.PokemonPagingSource
import me.lucasbatista.pokedex.data.repository.PokemonRepository
import javax.inject.Inject

@HiltViewModel
class PokemonsViewModel @Inject constructor(repository: PokemonRepository) :
    ViewModel() {
    val pokemons = Pager(
        config = PagingConfig(100),
        pagingSourceFactory = { PokemonPagingSource(repository) }
    ).flow
}