package com.nixstudio.moviemax.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nixstudio.moviemax.models.MovieEntity
import com.nixstudio.moviemax.models.TvShowsEntity
import com.nixstudio.moviemax.utils.DummyData

class HomeViewModel : ViewModel() {
    private val _listMovie = MutableLiveData<List<MovieEntity>>()
    private val _listTv = MutableLiveData<List<TvShowsEntity>>()

    private var _listMovieList = ArrayList<MovieEntity>()
    private var _listTvList = ArrayList<TvShowsEntity>()

    val listMovieList: ArrayList<MovieEntity>
        get() = _listMovieList

    val listTvList: ArrayList<TvShowsEntity>
        get() = _listTvList

    fun setMovies() {
        val movies = DummyData.generateLatestMovies()
        _listMovieList.addAll(movies)
        _listMovie.postValue(movies)
    }

    fun setTvShows() {
        val tvShows = DummyData.generateLatestTvShows()
        _listTvList.addAll(tvShows)
        _listTv.postValue(tvShows)
    }

    fun getTvShows(): LiveData<List<TvShowsEntity>> = _listTv

    fun getMovies(): LiveData<List<MovieEntity>> = _listMovie

}