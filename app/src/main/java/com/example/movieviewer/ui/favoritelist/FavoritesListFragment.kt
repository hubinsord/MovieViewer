package com.example.movieviewer.ui.favoritelist

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieviewer.R
import com.example.movieviewer.databinding.FragmentFavoritesListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesListFragment : Fragment(R.layout.fragment_favorites_list), FavoritesListAdapter.Companion.ClickListener {

    private val viewModel: FavoritesListViewModel by viewModels()
    private var _binding: FragmentFavoritesListBinding? = null
    private val binding get() = _binding!!
    private val favoritesListAdapter = FavoritesListAdapter(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentFavoritesListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this
        initViews()
        intObservers()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getMovies()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initViews() {
        initFavoritesRecyclerView()
    }

    private fun initFavoritesRecyclerView() {
        binding.rcvFavoriteMovies.apply {
            adapter = favoritesListAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun intObservers() {
        initMovieListObserver()
        initErrorObserver()
    }

    private fun initMovieListObserver() {
        viewModel.movieList.observe(viewLifecycleOwner) { favoritesListAdapter.submitList(it) }
    }

    private fun initErrorObserver() {
        viewModel.error.observe(viewLifecycleOwner) { showErrorDialog(it) }
    }

    private fun showErrorDialog(error: String?) {
        val dialog: AlertDialog = AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.title_error))
            .setMessage(error)
            .create()
        dialog.show()
    }

    override fun onFavoriteClicked(id: String, isFavorite: Boolean) {
        viewModel.onFavoriteClicked(id, isFavorite)
    }

    companion object {
        @JvmStatic
        fun newInstance() = FavoritesListFragment()
    }
}