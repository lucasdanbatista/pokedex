package me.lucasbatista.pokedex.ui.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import me.lucasbatista.pokedex.R

enum class PokemonType {
    GRASS, FIRE, ELECTRIC,
}

@Composable
fun PokemonView() {
    Column {
        PokemonTypeLabel(PokemonType.GRASS)
        PokemonTypeLabel(PokemonType.ELECTRIC)
        PokemonTypeLabel(PokemonType.FIRE)
    }
}

@Composable
private fun PokemonTypeLabel(type: PokemonType) {
    val text: String
    val color: Color
    when (type) {
        PokemonType.GRASS -> {
            text = "GRASS"
            color = colorResource(R.color.grass)
        }
        PokemonType.FIRE -> {
            text = "FIRE"
            color = colorResource(R.color.fire)
        }
        PokemonType.ELECTRIC -> {
            text = "ELECTRIC"
            color = colorResource(R.color.electric)
        }
    }
    Surface(
        modifier = Modifier.padding(16.dp),
        color = color,
        shape = RoundedCornerShape(16.dp),
        content = {
            Text(
                text = text,
                style = MaterialTheme.typography.caption,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(8.dp)
            )
        }
    )
}