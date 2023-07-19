package com.example.movieviewer.ui.movie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.Glide
import com.example.movieviewer.R
import com.example.movieviewer.data.entities.Movie
import com.example.movieviewer.databinding.FragmentMovieBinding
import com.example.movieviewer.domain.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MovieFragment : Fragment(R.layout.fragment_movie) {

    private val viewModel: MovieViewModel by viewModels()
    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
        initObservers()
    }

    private fun initListeners() {
        binding.apply {
            ivRefreshMovie.setOnClickListener {
                viewModel.onRefreshMovieClicked()
            }
            ivFavorite.setOnClickListener {
                viewModel.onIsFavoriteClicked()
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initObservers() {
        initMovieObserver()
        initIsfavoriteObserver()
    }

    private fun initIsfavoriteObserver() {
        viewModel.isFavorite.observe(viewLifecycleOwner) { isFav ->
            binding.ivFavorite.apply {
//                if (isFav) {
//                    this.setBackgroundResource(R.drawable.iv_favorite_filled)
//                } else {
//                    this.setBackgroundResource(R.drawable.iv_favorite_outline)
//                }
            }
        }
    }

    private fun initMovieObserver() {
        viewModel.movie.observe(viewLifecycleOwner) { movie ->
            loadMoviePoster(movie)
            setIsFavoriteImageResource(movie)
            binding.tvReleaseYear.text = movie?.releaseYear.toString() ?: "none"
            binding.tvTitle.text = movie?.title ?: ""
        }
    }

    private fun setIsFavoriteImageResource(movie: Movie?) {
        if (movie!!.isFavorite) {
            binding.ivFavorite.setBackgroundResource(R.drawable.iv_favorite_filled)
        } else {
            binding.ivFavorite.setBackgroundResource(R.drawable.iv_favorite_outline)
        }
    }

    private fun loadMoviePoster(movie: Movie?) {
        binding.apply {
            Glide.with(root)
                .load(movie?.imageUrl)
                .placeholder(R.drawable.iv_image_placeholder)
                .into(ivMoviePoster)
        }
    }


    companion object {
        @JvmStatic
        fun newInstance() = MovieFragment()
    }

}
