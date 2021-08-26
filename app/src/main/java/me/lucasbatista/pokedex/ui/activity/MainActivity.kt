package me.lucasbatista.pokedex.ui.activity

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import me.lucasbatista.pokedex.ui.view.PokemonListView
import me.lucasbatista.pokedex.ui.view.PokemonView
import me.lucasbatista.pokedex.ui.view_model.PokemonsViewModel

@ExperimentalCoilApi
@ExperimentalAnimationApi
@ExperimentalMaterialApi
@ExperimentalFoundationApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: PokemonsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.pokemons.observe(this) { pokemons ->
            setContent {
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
        }
    }
}