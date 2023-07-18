package com.example.movieviewer.ui.movie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.movieviewer.R
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
        binding.ivRefreshMovie.setOnClickListener {
            Timber.tag("TEST03").d("refresh clicked")
            viewModel.onRefreshMovieClicked()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initObservers() {
        viewModel.movie.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {
                    Toast.makeText(requireContext(), "Loading", Toast.LENGTH_SHORT).show()
                    Timber.tag("TEST02").d("Loading: ${it.error}")
                }

                is Resource.Success -> {
                    binding.apply {
                        Glide.with(root)
                            .load(it.data?.imageUrl)
//                            .placeholder()
                            .into(ivMoviePoster)
                        tvReleaseYear.text = it.data?.releaseYear.toString() ?: "none"
                        tvTitle.text = it.data?.title ?: ""
                    }
                }

                is Resource.Error -> {
                    Toast.makeText(requireContext(), it.error, Toast.LENGTH_SHORT).show()
                    Timber.tag("TEST01").d("error: ${it.error}")
                }
            }
        }
    }
}
