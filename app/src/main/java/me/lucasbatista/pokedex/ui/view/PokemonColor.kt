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
    }
}