package com.example.movieviewer.ui.favorites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.movieviewer.R
import com.example.movieviewer.data.entities.Movie
import com.example.movieviewer.databinding.ItemFavoriteListBinding
import timber.log.Timber

class FavoritesAdapter(private val listener: FavoritesAdapter.Companion.ClickListener) :
    ListAdapter<Movie, FavoritesAdapter.ViewHolder>(MovieComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesAdapter.ViewHolder {
        val binding = ItemFavoriteListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoritesAdapter.ViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    class MovieComparator : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean = oldItem == newItem
    }

    inner class ViewHolder(private val binding: ItemFavoriteListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val item = getItem(position)
                    listener.onFavoriteClicked(item.id, item.isFavorite)
                }
            }
        }

        fun bind(movie: Movie) = with(binding) {
            tvMovieTitle.text = movie.title
            ivIsFavorite.setImageResource(movie.favoriteDrawable)
        }
    }

    companion object {
        interface ClickListener {
            fun onFavoriteClicked(id: String, isFavorite: Boolean)
        }
    }
}