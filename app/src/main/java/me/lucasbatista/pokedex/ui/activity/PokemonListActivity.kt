package me.lucasbatista.pokedex.ui.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import me.lucasbatista.pokedex.R
import me.lucasbatista.pokedex.databinding.ActivityPokemonListBinding
import me.lucasbatista.pokedex.ui.adapter.PokemonListAdapter
import me.lucasbatista.pokedex.ui.view_model.PokemonListViewModel

class PokemonListActivity : AppCompatActivity() {
    private val viewModel: PokemonListViewModel by viewModels()
    private lateinit var binding: ActivityPokemonListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_pokemon_list)
        binding.recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        binding.recyclerView.adapter = PokemonListAdapter()
        viewModel.pokemons.observe(this) { (binding.recyclerView.adapter as PokemonListAdapter).submitList(it) }
    }
}
