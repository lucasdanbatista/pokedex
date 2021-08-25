package me.lucasbatista.pokedex.ui.controller

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import me.lucasbatista.pokedex.ui.view.PokemonView
import me.lucasbatista.pokedex.ui.view_model.PokemonViewModel

class PokemonActivity : ComponentActivity() {
    private val viewModel: PokemonViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.pokemon.observe(this) {
            if (it != null) {
                setContent { PokemonView(it) }
            }
        }
    }
}
