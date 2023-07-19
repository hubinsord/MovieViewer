package com.example.movieviewer.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.movieviewer.databinding.ActivityMainBinding
import com.example.movieviewer.ui.favorites.FavoritesFragment
import com.example.movieviewer.ui.movie.MovieFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val fragmentList = arrayListOf<Fragment>(
            MovieFragment.newInstance(),
            FavoritesFragment.newInstance()
        )
        binding.viewPager.adapter = MainFragmentPagerAdapter(fragmentList, supportFragmentManager, lifecycle)
    }
}