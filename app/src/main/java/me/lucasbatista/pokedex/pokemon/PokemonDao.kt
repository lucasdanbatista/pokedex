package me.lucasbatista.pokedex.pokemon

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDao {
    @Query("select * from pokemon where id = :id")
    fun find(id: Int): Flow<Pokemon>

    @Query("select * from pokemon")
    fun findAll(): Flow<List<Pokemon>>

    @Insert
    fun insert(pokemon: Pokemon)

    @Update
    fun update(pokemon: Pokemon)

    @Query("select exists(select * from pokemon where id = :id)")
    fun exists(id: Int): Boolean
}
