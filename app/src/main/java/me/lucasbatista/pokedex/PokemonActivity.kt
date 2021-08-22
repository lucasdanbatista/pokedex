package me.lucasbatista.pokedex

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import dagger.hilt.android.AndroidEntryPoint
import me.lucasbatista.pokedex.databinding.ActivityPokemonBinding

@AndroidEntryPoint
class PokemonActivity : AppCompatActivity() {
    private val viewModel: PokemonViewModel by viewModels()
    private lateinit var binding: ActivityPokemonBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_pokemon)
        binding.viewModel = viewModel
    }
}
