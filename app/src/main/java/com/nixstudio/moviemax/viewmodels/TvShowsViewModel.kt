package com.nixstudio.moviemax.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.nixstudio.moviemax.data.sources.MovieMaxRepository
import com.nixstudio.moviemax.data.sources.remote.DiscoverTvResultsItem

class TvShowsViewModel(private val repo: MovieMaxRepository) : ViewModel() {
    fun getTvShows(): LiveData<List<DiscoverTvResultsItem>> = repo.getDiscoveryTvShows()
}