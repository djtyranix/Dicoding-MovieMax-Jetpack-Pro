package com.nixstudio.moviemax.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nixstudio.moviemax.models.MovieEntity
import com.nixstudio.moviemax.models.TvShowsEntity

class ItemDetailViewModel : ViewModel() {
    private val currentMovie = MutableLiveData<MovieEntity>()
    private val currentTvShows = MutableLiveData<TvShowsEntity>()

    private lateinit var _currentMovieItem: MovieEntity
    private lateinit var _currentTvShowsItem: TvShowsEntity

    val currentMovieItem: MovieEntity
        get() = _currentMovieItem

    val currentTvShowsItem: TvShowsEntity
        get() = _currentTvShowsItem

//    0 = Movie, 1 = TvShows
    var _mode: Int = 0

    fun setCurrrentMovie(movie: MovieEntity) {
        currentMovie.postValue(movie)
        _currentMovieItem = movie
        _mode = 0
    }

    fun setCurrentTvShows(tvShows: TvShowsEntity) {
        currentTvShows.postValue(tvShows)
        _currentTvShowsItem = tvShows
        _mode = 1
    }

    fun getCurrentMovie(): LiveData<MovieEntity> = currentMovie

    fun getCurrentTvShows(): LiveData<TvShowsEntity> = currentTvShows

    fun getMode(): Int = _mode
}