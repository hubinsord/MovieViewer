package com.example.movieviewer.ui.favoritelist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieviewer.R
import com.example.movieviewer.data.entities.Movie
import com.example.movieviewer.databinding.FragmentFavoritesListBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

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
        initFavoritesListRecyclerView()
//        initListeners()
    }

    private fun initFavoritesListRecyclerView() {
        binding.rcvFavoriteMovies.apply {
            adapter = favoritesListAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun initListeners() {

    }

    private fun intObservers() {
        viewModel.movieList.observe(viewLifecycleOwner){
          favoritesListAdapter.submitList(it)
        }
    }

    override fun onFavoriteClicked(id: String) {

    }

    companion object {
        @JvmStatic
        fun newInstance() = FavoritesListFragment()
    }
}