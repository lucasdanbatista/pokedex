package me.lucasbatista.pokedex.persistence

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Pokemon(
    @PrimaryKey
    val id: Int,
    val name: String,
    val avatar: String,
    val specie: Specie
)

enum class Specie {
    GRASS, FIRE, ELECTRIC,
}
