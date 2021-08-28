package me.lucasbatista.pokedex.ui.view_model

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import dagger.hilt.android.lifecycle.HiltViewModel
import me.lucasbatista.pokedex.entity.Pokemon
import me.lucasbatista.pokedex.repository.PokemonRepository
import javax.inject.Inject

@HiltViewModel
class PokemonsViewModel @Inject constructor(repository: PokemonRepository) :
    ViewModel() {
    val pokemons = Pager(
        config = PagingConfig(100),
        pagingSourceFactory = { PokemonPagingSource(repository) }
    ).flow
}

class PokemonPagingSource constructor(private val repository: PokemonRepository) : PagingSource<Int, Pokemon>() {
    override fun getRefreshKey(state: PagingState<Int, Pokemon>) = state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Pokemon> {
        val pagedResult = repository.fetch(params.key)
        return LoadResult.Page(
            data = pagedResult.items,
            prevKey = pagedResult.previous,
            nextKey = pagedResult.next
        )
    }
}