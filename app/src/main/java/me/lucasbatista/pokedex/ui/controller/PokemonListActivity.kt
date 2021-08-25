package me.lucasbatista.pokedex.ui.controller

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import dagger.hilt.android.AndroidEntryPoint
import me.lucasbatista.pokedex.ui.view.PokemonListView
import me.lucasbatista.pokedex.ui.view_model.PokemonListViewModel

@AndroidEntryPoint
class PokemonListActivity : AppCompatActivity() {
    private val viewModel: PokemonListViewModel by viewModels()

    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.pokemons.observe(this) {
            if (it.isNotEmpty()) setContent { PokemonListView(it) }
        }
    }
}