package me.lucasbatista.pokedex.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Statistic(
    val title: String,
    val romId: Int,
    val percentage: Double
) : Parcelable
