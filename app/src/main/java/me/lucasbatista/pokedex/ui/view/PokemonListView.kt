package me.lucasbatista.pokedex.ui.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import me.lucasbatista.pokedex.persistence.Pokemon

@ExperimentalFoundationApi
@Composable
fun PokemonListView(pokemons: List<Pokemon>) {
    LazyVerticalGrid(
        cells = GridCells.Fixed(3),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(pokemons.size) { index -> PokemonCard(pokemons[index]) }
    }
}

@Composable
private fun PokemonCard(pokemon: Pokemon) {
    Card(
        modifier = Modifier
            .defaultMinSize(minHeight = 112.dp)
            .fillMaxWidth()
            .padding(4.dp),
        border = BorderStroke(2.dp, PokemonColor(pokemon)),
        elevation = 0.dp,
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
                        text = "#00${pokemon.id}",
                        color = PokemonColor(pokemon),
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
                        color = PokemonColor(pokemon),
                        content = {
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp),
                                text = pokemon.name,
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
