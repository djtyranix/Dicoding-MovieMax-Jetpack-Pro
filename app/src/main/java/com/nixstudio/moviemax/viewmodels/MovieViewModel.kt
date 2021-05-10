package com.nixstudio.moviemax.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nixstudio.moviemax.data.sources.MovieMaxRepository
import com.nixstudio.moviemax.data.sources.remote.DiscoverMovieResultsItem
import kotlinx.coroutines.launch

class MovieViewModel(private val repo: MovieMaxRepository) : ViewModel() {
    fun getMovies(): LiveData<List<DiscoverMovieResultsItem>> = repo.getDiscoveryMovie()
}