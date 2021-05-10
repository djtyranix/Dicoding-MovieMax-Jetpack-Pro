package com.nixstudio.moviemax.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nixstudio.moviemax.data.sources.MovieMaxRepository
import com.nixstudio.moviemax.data.sources.remote.DiscoverTvResultsItem
import kotlinx.coroutines.launch

class TvShowsViewModel(private val repo: MovieMaxRepository) : ViewModel() {
    fun getTvShows(): LiveData<List<DiscoverTvResultsItem>> = repo.getDiscoveryTvShows()
}