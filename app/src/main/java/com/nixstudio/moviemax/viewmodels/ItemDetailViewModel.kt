package com.nixstudio.moviemax.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nixstudio.moviemax.models.MovieEntity
import com.nixstudio.moviemax.models.TvShowsEntity

class ItemDetailViewModel : ViewModel() {
    private val currentMovie = MutableLiveData<MovieEntity>()
    private val currentTvShows = MutableLiveData<TvShowsEntity>()

//    0 = Movie, 1 = TvShows
    private var mode = MutableLiveData<Int>()

    fun setCurrrentMovie(movie: MovieEntity) {
        currentMovie.postValue(movie)
        mode.postValue(0)
    }

    fun setCurrentTvShows(tvShows: TvShowsEntity) {
        currentTvShows.postValue(tvShows)
        mode.postValue(1)
    }

    fun getCurrentMovie(): LiveData<MovieEntity> = currentMovie

    fun getCurrentTvShows(): LiveData<TvShowsEntity> = currentTvShows

    fun getMode(): LiveData<Int> = mode
}