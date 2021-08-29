package me.lucasbatista.pokedex.ui.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import me.lucasbatista.pokedex.R
import me.lucasbatista.pokedex.data.entity.Pokemon
import me.lucasbatista.pokedex.data.entity.Type

@Composable
fun pokemonColor(pokemon: Pokemon): Color {
    return when (pokemon.type) {
        Type.GRASS -> colorResource(R.color.type_grass)
        Type.FIRE -> colorResource(R.color.type_fire)
        Type.ELECTRIC -> colorResource(R.color.type_electric)
        Type.WATER -> colorResource(R.color.type_water)
        Type.GHOST -> colorResource(R.color.type_ghost)
        Type.PSYCHIC -> colorResource(R.color.type_psychic)
        Type.NORMAL -> colorResource(R.color.type_normal)
        Type.ICE -> colorResource(R.color.type_ice)
        Type.FIGHTING -> colorResource(R.color.type_fighting)
        Type.POISON -> colorResource(R.color.type_poison)
        Type.GROUND -> colorResource(R.color.type_ground)
        Type.FLYING -> colorResource(R.color.type_flying)
        Type.BUG -> colorResource(R.color.type_bug)
        Type.ROCK -> colorResource(R.color.type_rock)
        Type.DARK -> colorResource(R.color.type_dark)
        Type.DRAGON -> colorResource(R.color.type_dragon)
        Type.STEEL -> colorResource(R.color.type_steel)
        Type.FAIRY -> colorResource(R.color.type_fairy)
    }
}