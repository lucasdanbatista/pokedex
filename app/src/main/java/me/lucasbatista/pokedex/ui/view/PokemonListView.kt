package me.lucasbatista.pokedex.ui.view

import android.os.Bundle
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.compose.LazyPagingItems
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.google.accompanist.systemuicontroller.SystemUiController
import me.lucasbatista.pokedex.R
import me.lucasbatista.pokedex.data.entity.Pokemon

@ExperimentalMaterialApi
@ExperimentalCoilApi
@ExperimentalFoundationApi
@Composable
fun PokemonListView(
    pokemons: LazyPagingItems<Pokemon>,
    navController: NavController,
    uiController: SystemUiController
) {
    uiController.setStatusBarColor(Color.White)
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(
                    start = 24.dp,
                    end = 16.dp
                )
                .defaultMinSize(minHeight = 56.dp)
                .fillMaxWidth(),
            content = {
                Image(
                    painter = painterResource(R.drawable.ic_pokeball),
                    colorFilter = ColorFilter.tint(Color.Black),
                    contentDescription = "",
                    modifier = Modifier.size(24.dp)
                )
                Text(
                    modifier = Modifier.padding(horizontal = 8.dp),
                    text = stringResource(R.string.app_name),
                    style = MaterialTheme.typography.h5,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                )
            }
        )
        Divider()
        LazyVerticalGrid(
            cells = GridCells.Fixed(3),
            contentPadding = PaddingValues(16.dp),
            content = {
                items(pokemons.itemCount) { index ->
                    if (pokemons.peek(index) != null) {
                        PokemonCard(
                            pokemon = pokemons[index]!!,
                            onClickListener = {
                                navController.currentBackStackEntry!!.arguments = Bundle().apply {
                                    putParcelable("pokemon", it)
                                }
                                navController.navigate("/pokemon/details")
                            }
                        )
                    }
                }
            }
        )
    }
}

@ExperimentalMaterialApi
@ExperimentalCoilApi
@Composable
private fun PokemonCard(pokemon: Pokemon, onClickListener: ((pokemon: Pokemon) -> Unit)) {
    Card(
        modifier = Modifier
            .defaultMinSize(minHeight = 112.dp)
            .fillMaxWidth()
            .padding(4.dp),
        border = BorderStroke(2.dp, pokemonColor(pokemon)),
        elevation = 0.dp,
        onClick = { onClickListener(pokemon) },
        content = {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                content = {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                top = 8.dp,
                                end = 8.dp
                            ),
                        text = when {
                            pokemon.id < 10 -> "#00${pokemon.id}"
                            pokemon.id < 100 -> "#0${pokemon.id}"
                            else -> "#${pokemon.id}"
                        },
                        color = pokemonColor(pokemon),
                        style = MaterialTheme.typography.overline,
                        textAlign = TextAlign.End,
                    )
                    Image(
                        painter = rememberImagePainter(pokemon.avatar),
                        contentDescription = "",
                        modifier = Modifier
                            .size(80.dp)
                            .padding(8.dp)
                    )
                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        color = pokemonColor(pokemon),
                        content = {
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp),
                                text = pokemon.name,
                                overflow = TextOverflow.Ellipsis,
                                maxLines = 1,
                                textAlign = TextAlign.Center,
                                style = MaterialTheme.typography.caption,
                                color = Color.White,
                            )
                        }
                    )
                }
            )
        }
    )
}
