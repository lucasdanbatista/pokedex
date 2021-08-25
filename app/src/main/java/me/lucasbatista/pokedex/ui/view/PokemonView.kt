package me.lucasbatista.pokedex.ui.view

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import me.lucasbatista.pokedex.R
import me.lucasbatista.pokedex.persistence.Pokemon
import me.lucasbatista.pokedex.persistence.Specie

@Preview
@Composable
fun PokemonView(@PreviewParameter(PokemonPreview::class) pokemon: Pokemon) {
    Box(
        modifier = Modifier.fillMaxSize(),
        content = {
            Box(
                modifier = Modifier
                    .background(primaryColor(pokemon))
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
                                            Specie(pokemon)
                                            AboutSection(pokemon)
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
                    .padding(top = 68.dp),
                contentAlignment = Alignment.TopCenter,
                content = {
                    Image(
                        painter = rememberImagePainter(pokemon.avatar),
                        contentDescription = "",
                        modifier = Modifier.size(172.dp)
                    )
                }
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 8.dp),
                content = {
                    IconButton(
                        onClick = { TODO() },
                        content = {
                            Icon(
                                painter = painterResource(R.drawable.ic_arrow_left),
                                tint = Color.White,
                                contentDescription = "",
                            )
                        }
                    )
                    Text(
                        text = pokemon.name,
                        style = MaterialTheme.typography.h6,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            )
        }
    )
}

@Composable
private fun primaryColor(pokemon: Pokemon): Color {
    return when (pokemon.specie) {
        Specie.GRASS -> colorResource(R.color.grass)
        Specie.FIRE -> colorResource(R.color.fire)
        Specie.ELECTRIC -> colorResource(R.color.electric)
    }
}

@Composable
private fun AboutSection(pokemon: Pokemon) {
    Text(
        text = "About",
        style = MaterialTheme.typography.subtitle1,
        fontWeight = FontWeight.Bold,
        color = primaryColor(pokemon)
    )
    Row(
        modifier = Modifier.padding(vertical = 16.dp),
        content = {
            AboutProperty(
                drawable = R.drawable.ic_balance,
                value = "${pokemon.weight} kg",
                caption = "Weight"
            )
            AboutProperty(
                drawable = R.drawable.ic_ruler,
                value = "${pokemon.height} m",
                caption = "Height"
            )
            AboutProperty(
                value = pokemon.totalMoves.toString(),
                caption = "Moves"
            )
        }
    )
    Text(
        text = pokemon.description,
        style = MaterialTheme.typography.caption
    )
}

@Composable
private fun AboutProperty(
    @DrawableRes
    drawable: Int? = null,
    value: String,
    caption: String,
) {
    Box(
        modifier = Modifier.padding(horizontal = 8.dp),
        content = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                content = {
                    Box(
                        modifier = Modifier.defaultMinSize(minHeight = 24.dp),
                        contentAlignment = Alignment.Center,
                        content = {
                            if (drawable != null) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    content = {
                                        Icon(
                                            painter = painterResource(drawable),
                                            contentDescription = ""
                                        )
                                        Text(
                                            text = value,
                                            style = MaterialTheme.typography.caption,
                                        )
                                    }
                                )
                            } else {
                                Text(
                                    text = value,
                                    style = MaterialTheme.typography.subtitle1,
                                    fontWeight = FontWeight.Bold,
                                )
                            }
                        }
                    )
                    Text(
                        text = caption,
                        style = MaterialTheme.typography.overline,
                    )
                }
            )
        }
    )
}

@Composable
private fun Specie(pokemon: Pokemon) {
    val text: String
    val color: Color
    when (pokemon.specie) {
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
        modifier = Modifier.padding(vertical = 16.dp),
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

class PokemonPreview : PreviewParameterProvider<Pokemon> {
    override val values: Sequence<Pokemon>
        get() = sequenceOf(
            Pokemon(
                id = 1,
                name = "Bulbasaur",
                description = "There is a plant seed on its back right from the day this Pokémon is born. The seed slowly grows larger.",
                avatar = "https://i.ibb.co/d6NJ2L3/pokemon-bulbasaur.png",
                specie = Specie.GRASS,
                height = 0.7,
                weight = 6.9,
                totalMoves = 7
            )
        )
}