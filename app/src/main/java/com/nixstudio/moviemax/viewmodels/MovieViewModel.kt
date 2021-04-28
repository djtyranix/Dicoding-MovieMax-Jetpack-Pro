package com.nixstudio.moviemax.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nixstudio.moviemax.models.MovieEntity
import com.nixstudio.moviemax.utils.DummyData

class MovieViewModel : ViewModel() {
    private val listMovie = MutableLiveData<List<MovieEntity>>()
    private val _listMovieList = ArrayList<MovieEntity>()

    val listMovieList: ArrayList<MovieEntity>
        get() = _listMovieList

    fun setMovies() {
        val movies = DummyData.generateMovies()
        listMovie.postValue(movies)
        _listMovieList.addAll(movies)
    }

    fun getMovies(): LiveData<List<MovieEntity>> = listMovie
}