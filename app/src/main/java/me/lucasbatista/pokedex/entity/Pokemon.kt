package me.lucasbatista.pokedex.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class Pokemon(
    @PrimaryKey
    val id: Int,
    val name: String,
    val avatar: String,
    val specie: Specie,
    val height: Double,
    val weight: Double,
    val totalMoves: Int,
    val stats: List<Statistic>
) : Parcelable