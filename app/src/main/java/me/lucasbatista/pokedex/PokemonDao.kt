package me.lucasbatista.pokedex

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDao {
    @Query("select * from pokemon where id = :id")
    fun find(id: Int): Flow<Pokemon?>

    @Insert
    fun create(pokemon: Pokemon)
}
