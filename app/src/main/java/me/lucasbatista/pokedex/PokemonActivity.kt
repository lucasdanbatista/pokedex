package me.lucasbatista.pokedex

import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokemonActivity : AppCompatActivity() {
    private val viewModel: PokemonViewModel by viewModels()

    override fun onResume() {
        super.onResume()
        viewModel.pokemon.observe(this) { Log.i("Pokemon", it.name) }
    }
}
