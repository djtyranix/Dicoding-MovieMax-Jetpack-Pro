package com.nixstudio.moviemax.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nixstudio.moviemax.models.MovieEntity
import com.nixstudio.moviemax.utils.DummyData

class MovieViewModel : ViewModel() {
    private val listMovie = MutableLiveData<List<MovieEntity>>()

    fun setMovies() {
        val movies = DummyData.generateMovies()
        listMovie.postValue(movies)
    }

    fun getMovies(): LiveData<List<MovieEntity>> = listMovie
}