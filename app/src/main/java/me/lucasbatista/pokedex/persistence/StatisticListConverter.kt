package me.lucasbatista.pokedex.persistence

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class StatisticListConverter {
    private val type = object : TypeToken<List<Statistic>>() {}.type

    @TypeConverter
    fun fromJson(json: String): List<Statistic> = Gson().fromJson(json, type)

    @TypeConverter
    fun toJson(stats: List<Statistic>): String = Gson().toJson(stats, type)
}