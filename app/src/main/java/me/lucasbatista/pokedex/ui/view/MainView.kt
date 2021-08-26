package me.lucasbatista.pokedex.ui.view

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import me.lucasbatista.pokedex.persistence.Pokemon

@ExperimentalAnimationApi
@ExperimentalCoilApi
@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun MainView(pokemons: List<Pokemon>) {
    val uiController = rememberSystemUiController()
    val navController = rememberAnimatedNavController()
    AnimatedNavHost(navController, "/pokemons") {
        composable("/pokemons") {
            PokemonListView(pokemons, navController, uiController)
        }
        composable("/pokemons/{id}") {
            val pokemon = pokemons.first { p -> p.id == it.arguments!!.getString("id")!!.toInt() }
            PokemonView(pokemon, navController, uiController)
        }
    }
}