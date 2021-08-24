package me.lucasbatista.pokedex.ui.controller

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import me.lucasbatista.pokedex.ui.view.PokemonView

@AndroidEntryPoint
class PokemonActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { PokemonView() }
    }
}
