package me.lucasbatista.pokedex.data.cache

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import me.lucasbatista.pokedex.data.entity.Pokemon

@Dao
interface PokemonDao {
    @Query("select * from pokemon")
    fun findAll(): PagingSource<Int, Pokemon>

    @Insert
    fun insert(pokemon: Pokemon)

    @Update
    fun update(pokemon: Pokemon)

    @Query("select exists(select * from pokemon where id = :id)")
    fun exists(id: Int): Boolean
}
