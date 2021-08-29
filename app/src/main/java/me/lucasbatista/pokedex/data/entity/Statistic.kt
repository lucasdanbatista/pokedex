package me.lucasbatista.pokedex.data.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Statistic(
    val title: String,
    val percentage: Double
) : Parcelable
