package com.example.movieviewer.ui.favoritelist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.movieviewer.data.entities.Movie
import com.example.movieviewer.databinding.ItemFavoriteListBinding

class FavoritesListAdapter(private val listener: FavoritesListAdapter.Companion.ClickListener) : ListAdapter<Movie, FavoritesListAdapter.ViewHolder>(MovieComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesListAdapter.ViewHolder {
        val binding = ItemFavoriteListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoritesListAdapter.ViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }    }

    class MovieComparator : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }

    inner class ViewHolder(private val binding: ItemFavoriteListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val id = getItem(position).id
                    listener.onFavoriteClicked(id)
                }
            }
        }

        fun bind(movie: Movie){
            binding.apply {
                tvMovieTitle.text = movie.title
            }
        }
    }

    companion object {
        interface ClickListener {
            fun onFavoriteClicked(id: String)
        }
    }
}