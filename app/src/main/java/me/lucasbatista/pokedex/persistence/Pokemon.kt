package me.lucasbatista.pokedex.persistence

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Pokemon(
    @PrimaryKey
    val id: Int,
    val name: String,
    val description: String,
    val avatar: String,
    val specie: Specie,
    val height: Double,
    val weight: Double,
    val totalMoves: Int,
)

enum class Specie {
    GRASS, FIRE, ELECTRIC,
}
