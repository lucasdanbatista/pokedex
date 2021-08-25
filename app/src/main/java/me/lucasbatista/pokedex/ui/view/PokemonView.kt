package me.lucasbatista.pokedex.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import me.lucasbatista.pokedex.R
import me.lucasbatista.pokedex.persistence.Pokemon
import me.lucasbatista.pokedex.persistence.Specie

@Composable
fun PokemonView(pokemon: Pokemon) {
    Box(
        modifier = Modifier.fillMaxSize(),
        content = {
            Box(
                modifier = Modifier
                    .background(colorResource(R.color.grass))
                    .fillMaxSize(),
                content = {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        content = {
                            Box(
                                contentAlignment = Alignment.TopEnd,
                                modifier = Modifier.fillMaxWidth(),
                                content = {
                                    Image(
                                        painter = painterResource(R.drawable.ic_pokeball),
                                        alpha = 0.1F,
                                        colorFilter = ColorFilter.tint(Color.White),
                                        contentDescription = "",
                                        modifier = Modifier
                                            .size(200.dp)
                                            .padding(16.dp)
                                    )
                                }
                            )
                            Card(
                                backgroundColor = Color.White,
                                shape = RoundedCornerShape(8.dp),
                                elevation = 0.dp,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(
                                        bottom = 8.dp,
                                        end = 8.dp,
                                        start = 8.dp
                                    ),
                                content = {
                                    Column(
                                        modifier = Modifier.padding(
                                            top = 24.dp,
                                            bottom = 16.dp,
                                            end = 16.dp,
                                            start = 16.dp
                                        ),
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        content = {
                                            PokemonTypeLabel(pokemon.specie)
                                        }
                                    )
                                },
                            )
                        }
                    )
                }
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 40.dp),
                contentAlignment = Alignment.TopCenter,
                content = {
                    Image(
                        painter = rememberImagePainter(pokemon.avatar),
                        contentDescription = "",
                        modifier = Modifier.size(200.dp)
                    )
                }
            )
        }
    )
}

@Composable
private fun PokemonTypeLabel(type: Specie) {
    val text: String
    val color: Color
    when (type) {
        Specie.GRASS -> {
            text = "GRASS"
            color = colorResource(R.color.grass)
        }
        Specie.FIRE -> {
            text = "FIRE"
            color = colorResource(R.color.fire)
        }
        Specie.ELECTRIC -> {
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