package com.bonifasiustrg.newsappmvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bonifasiustrg.newsappmvvm.repository.NewsRepository

class NewsViewModelProviderFactory(
    val newsRepository: NewsRepository
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NewsViewModel(newsRepository) as T
    }
}