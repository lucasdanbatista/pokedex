package me.lucasbatista.pokedex.ui.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.squareup.picasso.Picasso
import me.lucasbatista.pokedex.R
import me.lucasbatista.pokedex.databinding.ActivityPokemonListBinding
import me.lucasbatista.pokedex.databinding.ListItemPokemonBinding
import me.lucasbatista.pokedex.persistence.Pokemon
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

private class PokemonListAdapter : ListAdapter<Pokemon, PokemonListAdapter.ViewHolder>(DiffCallback()) {
    class ViewHolder(val binding: ListItemPokemonBinding) : RecyclerView.ViewHolder(binding.root)

    class DiffCallback : DiffUtil.ItemCallback<Pokemon>() {
        override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon) = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon) = oldItem == newItem
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.binding) {
            pokemon = getItem(position)
            Picasso.get()
                .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png")
                .into(imageView)
            executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(ListItemPokemonBinding.inflate(LayoutInflater.from(parent.context), parent, false))
}