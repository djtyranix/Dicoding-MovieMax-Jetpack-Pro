package com.nixstudio.moviemax.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.nixstudio.moviemax.data.entities.CombinedResultEntity
import com.nixstudio.moviemax.data.sources.MovieMaxRepository
import com.nixstudio.moviemax.data.sources.remote.DiscoverMovieResultsItem
import com.nixstudio.moviemax.data.sources.remote.DiscoverTvResultsItem

class HomeViewModel(private val repo: MovieMaxRepository) : ViewModel() {
    fun getTvShows(): LiveData<List<DiscoverTvResultsItem>> = repo.getDiscoveryTvShows()

    fun getMovies(): LiveData<List<DiscoverMovieResultsItem>> = repo.getDiscoveryMovie()

    fun getTrending(): LiveData<List<CombinedResultEntity>> = repo.getTrendingToday()
}