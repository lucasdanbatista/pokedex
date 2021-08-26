package me.lucasbatista.pokedex.ui.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import me.lucasbatista.pokedex.persistence.Pokemon

@ExperimentalCoilApi
@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun MainView(pokemons: List<Pokemon>) {
    val uiController = rememberSystemUiController()
    val navController = rememberNavController()
    NavHost(navController, "/pokemons") {
        composable("/pokemons") {
            PokemonListView(pokemons, navController, uiController)
        }
        composable("/pokemons/{id}") {
            val pokemon = pokemons.first { p -> p.id == it.arguments!!.getString("id")!!.toInt() }
            PokemonView(pokemon, navController, uiController)
        }
    }
}