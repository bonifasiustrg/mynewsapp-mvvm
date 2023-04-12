package com.bonifasiustrg.newsappmvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider

import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.bonifasiustrg.newsappmvvm.databinding.ActivityMainBinding
import com.bonifasiustrg.newsappmvvm.db.ArticleDatabase
import com.bonifasiustrg.newsappmvvm.repository.NewsRepository
import com.bonifasiustrg.newsappmvvm.utils.isVisible


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel: NewsViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val newsRepository = NewsRepository(ArticleDatabase(this))
        val viewModelProviderFactory = NewsViewModelProviderFactory(newsRepository)

        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(NewsViewModel::class.java)



        setUpBottomNav()
    }


    private fun setUpBottomNav() {
        with(binding) {
            val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
            val navController = navHostFragment.navController
            bottomNav.setupWithNavController(navController)
            navController.addOnDestinationChangedListener { _, destination, _ ->
                when (destination.id) {
                    R.id.breakingNewsFragment, R.id.searchNewsFragment, R.id.savedNewsFragment, R.id.profileFragment -> bottomNav.isVisible(true)
                    else -> bottomNav.isVisible(true)
                }
            }
        }
    }
}