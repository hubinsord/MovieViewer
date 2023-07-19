package com.example.movieviewer.ui.movie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.movieviewer.R
import com.example.movieviewer.data.entities.Movie
import com.example.movieviewer.databinding.FragmentMovieBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieFragment : Fragment(R.layout.fragment_movie) {

    private val viewModel: MovieViewModel by viewModels()
    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
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
            ivRefreshMovie.setOnClickListener { viewModel.onRefreshMovieClicked() }
            ivFavorite.setOnClickListener { viewModel.onIsFavoriteClicked() }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initObservers() {
        initMovieObserver()
        initIsLoadingObserver()
    }

    private fun initMovieObserver() {
        viewModel.movie.observe(viewLifecycleOwner) { movie ->
            loadMoviePoster(movie)
            setIsFavoriteImageResource(movie)
            binding.tvReleaseYear.text = movie?.releaseYear.toString() ?: "none"
            binding.tvTitle.text = movie?.title ?: ""
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

    private fun initIsLoadingObserver() {
        viewModel.isFavorite.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBar.apply {
                if (isLoading) this.visibility = View.VISIBLE else this.visibility = View.INVISIBLE
            }
        }
    }

    private fun setIsFavoriteImageResource(movie: Movie?) {
        movie?.let { movie ->
            binding.ivFavorite.setBackgroundResource(
                if (movie.isFavorite) R.drawable.iv_favorite_filled else R.drawable.iv_favorite_outline
            )
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = MovieFragment()
    }

}
