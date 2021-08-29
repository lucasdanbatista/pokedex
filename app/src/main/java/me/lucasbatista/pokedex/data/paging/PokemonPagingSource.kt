package me.lucasbatista.pokedex.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import me.lucasbatista.pokedex.data.entity.Pokemon
import me.lucasbatista.pokedex.data.repository.PokemonRepository

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