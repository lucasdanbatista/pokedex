package me.lucasbatista.pokedex.persistence

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Pokemon(
    @PrimaryKey
    val id: Int,
    var name: String
)