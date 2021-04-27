package com.nixstudio.moviemax.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nixstudio.moviemax.models.MovieEntity
import com.nixstudio.moviemax.models.TvShowsEntity
import com.nixstudio.moviemax.utils.DummyData

class HomeViewModel : ViewModel() {
    private val listMovie = MutableLiveData<List<MovieEntity>>()
    private val listTv = MutableLiveData<List<TvShowsEntity>>()

    fun setMovies() {
        val movies = DummyData.generateLatestMovies()
        listMovie.postValue(movies)
    }

    fun setTvShows() {
        val tvShows = DummyData.generateLatestTvShows()
        listTv.postValue(tvShows)
    }

    fun getTvShows(): LiveData<List<TvShowsEntity>> = listTv

    fun getMovies(): LiveData<List<MovieEntity>> = listMovie

}