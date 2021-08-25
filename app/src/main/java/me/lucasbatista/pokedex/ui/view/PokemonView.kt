package me.lucasbatista.pokedex.ui.view

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import me.lucasbatista.pokedex.persistence.Statistic

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
                                        modifier = Modifier
                                            .verticalScroll(rememberScrollState())
                                            .padding(
                                                top = 40.dp,
                                                bottom = 16.dp,
                                                end = 16.dp,
                                                start = 16.dp
                                            ),
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        content = {
                                            Specie(pokemon)
                                            AboutSection(pokemon)
                                            StatsSection(pokemon)
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
                    .padding(top = 72.dp),
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
private fun StatsSection(pokemon: Pokemon) {
    SectionTitle("Base Stats", pokemon)
    Column(
        content = {
            for (stat in pokemon.stats) {
                StatsItem(pokemon, stat)
            }
        }
    )
}

@Composable
private fun StatsItem(pokemon: Pokemon, stat: Statistic) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        content = {
            Text(
                text = stat.title,
                modifier = Modifier.defaultMinSize(minWidth = 44.dp),
                color = primaryColor(pokemon),
                style = MaterialTheme.typography.subtitle2,
                fontWeight = FontWeight.Bold
            )
            Divider(
                color = Color.LightGray,
                modifier = Modifier
                    .height(19.dp)
                    .width(1.dp)
            )
            Text(
                modifier = Modifier.padding(horizontal = 8.dp),
                text = stat.romId.toString(),
            )
            LinearProgressIndicator(
                color = primaryColor(pokemon),
                backgroundColor = Color.LightGray,
                progress = stat.percentage.toFloat(),
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
private fun SectionTitle(title: String, pokemon: Pokemon) {
    Text(
        modifier = Modifier.padding(vertical = 16.dp),
        text = title,
        style = MaterialTheme.typography.subtitle1,
        fontWeight = FontWeight.Bold,
        color = primaryColor(pokemon)
    )
}

@Composable
private fun AboutSection(pokemon: Pokemon) {
    SectionTitle("About", pokemon)
    Row(
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
        modifier = Modifier.padding(top = 16.dp),
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
                totalMoves = 7,
                stats = listOf(
                    Statistic("HP", 45, 0.2),
                    Statistic("ATK", 49, 0.3),
                    Statistic("DEF", 49, 0.3),
                    Statistic("SATK", 65, 0.4),
                    Statistic("SDEF", 65, 0.4),
                    Statistic("SPD", 45, 0.2),
                )
            )
        )
}