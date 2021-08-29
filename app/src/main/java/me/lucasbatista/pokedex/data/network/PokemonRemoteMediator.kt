package me.lucasbatista.pokedex.data.network

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import me.lucasbatista.pokedex.data.cache.PokemonDao
import me.lucasbatista.pokedex.data.entity.Pokemon

@ExperimentalPagingApi
class PokemonRemoteMediator constructor(private val webService: PokemonWebService, private val dao: PokemonDao) :
    RemoteMediator<Int, Pokemon>() {

    override suspend fun load(loadType: LoadType, state: PagingState<Int, Pokemon>): MediatorResult {
        return withContext(CoroutineScope(IO).coroutineContext) {
            MediatorResult.Success(!canLoadNext(state))
        }
    }

    private suspend fun canLoadNext(state: PagingState<Int, Pokemon>): Boolean {
        return if (state.isEmpty()) {
            fetch(1)?.next != null
        } else {
            val page = findPage(state)
            if (page?.next != null) fetch(page.next)?.next != null
            else false
        }
    }

    private suspend fun findPage(state: PagingState<Int, Pokemon>): Page<Pokemon>? {
        return try {
            val response = webService.findById(state.lastItemOrNull()!!.id)
            return if (response.isSuccessful) return response.body()!! else null
        } catch (e: Exception) {
            null
        }
    }

    private suspend fun fetch(page: Int?): Page<Pokemon>? {
        return try {
            val response = webService.findByPage(page)
            return if (response.isSuccessful) {
                response.body()!!.items.forEach { if (dao.exists(it.id)) dao.update(it) else dao.insert(it) }
                response.body()!!
            } else null
        } catch (e: Exception) {
            null
        }
    }
}