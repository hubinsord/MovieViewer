package com.example.movieviewer.ui.movie

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
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
        initErrorObserver()
    }

    private fun showErrorDialog(error: String?) {
        val dialog: AlertDialog = AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.title_error))
            .setMessage(error)
            .create()
        dialog.show()
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
        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBar.isVisible = isLoading
        }
    }

    private fun initErrorObserver() {
        viewModel.error.observe(viewLifecycleOwner) {
            showErrorDialog(it)
        }
    }

    private fun setIsFavoriteImageResource(movie: Movie?) {
        movie?.let { binding.ivFavorite.setBackgroundResource(it.favoriteDrawable) }
    }

    companion object {
        @JvmStatic
        fun newInstance() = MovieFragment()
    }

}
