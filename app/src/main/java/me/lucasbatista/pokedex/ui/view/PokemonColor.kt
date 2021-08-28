package me.lucasbatista.pokedex.ui.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import me.lucasbatista.pokedex.R
import me.lucasbatista.pokedex.entity.Pokemon
import me.lucasbatista.pokedex.entity.Specie

@Composable
fun pokemonColor(pokemon: Pokemon): Color {
    return when (pokemon.specie) {
        Specie.GRASS -> colorResource(R.color.grass)
        Specie.FIRE -> colorResource(R.color.fire)
        Specie.ELECTRIC -> colorResource(R.color.electric)
        Specie.WATER -> colorResource(R.color.water)
        Specie.GHOST -> colorResource(R.color.ghost)
        Specie.PSYCHIC -> colorResource(R.color.psychic)
        Specie.NORMAL -> colorResource(R.color.normal)
        Specie.ICE -> colorResource(R.color.ice)
        Specie.FIGHTING -> colorResource(R.color.fighting)
        Specie.POISON -> colorResource(R.color.poison)
        Specie.GROUND -> colorResource(R.color.ground)
        Specie.FLYING -> colorResource(R.color.flying)
        Specie.BUG -> colorResource(R.color.bug)
        Specie.ROCK -> colorResource(R.color.rock)
        Specie.DARK -> colorResource(R.color.dark)
        Specie.DRAGON -> colorResource(R.color.dragon)
        Specie.STEEL -> colorResource(R.color.steel)
        Specie.FAIRY -> colorResource(R.color.fairy)
    }
}