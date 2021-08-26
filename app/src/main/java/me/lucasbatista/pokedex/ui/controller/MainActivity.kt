package me.lucasbatista.pokedex.ui.controller

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import dagger.hilt.android.AndroidEntryPoint
import me.lucasbatista.pokedex.ui.view.MainView
import me.lucasbatista.pokedex.ui.view_model.PokemonListViewModel

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@ExperimentalFoundationApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: PokemonListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.pokemons.observe(this) {
            if (it.isNotEmpty()) setContent { MainView(it) }
        }
    }
}