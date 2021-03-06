package com.nixstudio.moviemax.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.nixstudio.moviemax.data.sources.MovieMaxRepository
import com.nixstudio.moviemax.data.sources.remote.DiscoverMovieResultsItem

class MovieViewModel(private val repo: MovieMaxRepository) : ViewModel() {
    fun getMovies(): LiveData<List<DiscoverMovieResultsItem>> = repo.getDiscoveryMovie()
}