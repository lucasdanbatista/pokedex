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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.google.accompanist.systemuicontroller.SystemUiController
import me.lucasbatista.pokedex.R
import me.lucasbatista.pokedex.entity.Pokemon
import me.lucasbatista.pokedex.entity.Specie
import me.lucasbatista.pokedex.entity.Statistic

@ExperimentalCoilApi
@Composable
fun PokemonView(navController: NavController, uiController: SystemUiController) {
    val pokemon = navController.previousBackStackEntry?.arguments?.getParcelable<Pokemon>("pokemon")
    if (pokemon != null) {
        uiController.setStatusBarColor(pokemonColor(pokemon))
        Box(
            modifier = Modifier.fillMaxSize(),
            content = {
                Box(
                    modifier = Modifier
                        .background(pokemonColor(pokemon))
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
                                                SpecieLabel(pokemon)
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
                            onClick = {
                                navController.navigateUp()
                            },
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
}

@Composable
private fun StatsSection(pokemon: Pokemon) {
    SectionTitle(stringResource(R.string.stats), pokemon)
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
                color = pokemonColor(pokemon),
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
                text = stat.percentage.times(100).toString(),
            )
            LinearProgressIndicator(
                color = pokemonColor(pokemon),
                backgroundColor = Color.LightGray,
                progress = stat.percentage.toFloat(),
            )
        }
    )
}

@Composable
private fun SectionTitle(title: String, pokemon: Pokemon) {
    Text(
        modifier = Modifier.padding(vertical = 16.dp),
        text = title,
        style = MaterialTheme.typography.subtitle1,
        fontWeight = FontWeight.Bold,
        color = pokemonColor(pokemon)
    )
}

@Composable
private fun AboutSection(pokemon: Pokemon) {
    SectionTitle(stringResource(R.string.about), pokemon)
    Row(
        content = {
            AboutProperty(
                drawable = R.drawable.ic_balance,
                value = "${pokemon.weight} kg",
                caption = stringResource(R.string.weight)
            )
            AboutProperty(
                drawable = R.drawable.ic_ruler,
                value = "${pokemon.height} m",
                caption = stringResource(R.string.height)
            )
            AboutProperty(
                value = pokemon.totalMoves.toString(),
                caption = stringResource(R.string.moves)
            )
        }
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
private fun SpecieLabel(pokemon: Pokemon) {
    val text = when (pokemon.specie) {
        Specie.GRASS -> stringResource(R.string.specie_grass)
        Specie.FIRE -> stringResource(R.string.specie_fire)
        Specie.ELECTRIC -> stringResource(R.string.specie_electric)
        Specie.WATER -> stringResource(R.string.specie_water)
        Specie.GHOST -> stringResource(R.string.specie_ghost)
        Specie.PSYCHIC -> stringResource(R.string.specie_psychic)
        Specie.NORMAL -> stringResource(R.string.specie_normal)
        Specie.ICE -> stringResource(R.string.specie_ice)
        Specie.FIGHTING -> stringResource(R.string.specie_fighting)
        Specie.POISON -> stringResource(R.string.specie_poison)
        Specie.GROUND -> stringResource(R.string.specie_ground)
        Specie.FLYING -> stringResource(R.string.specie_flying)
        Specie.BUG -> stringResource(R.string.specie_bug)
        Specie.ROCK -> stringResource(R.string.specie_rock)
        Specie.DARK -> stringResource(R.string.specie_dark)
        Specie.DRAGON -> stringResource(R.string.specie_dragon)
        Specie.STEEL -> stringResource(R.string.specie_steel)
        Specie.FAIRY -> stringResource(R.string.specie_fairy)
    }.uppercase()
    Surface(
        color = pokemonColor(pokemon),
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