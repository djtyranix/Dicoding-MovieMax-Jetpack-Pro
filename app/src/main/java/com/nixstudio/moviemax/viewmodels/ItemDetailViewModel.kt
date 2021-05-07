package com.nixstudio.moviemax.viewmodels

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nixstudio.moviemax.models.MovieEntity
import com.nixstudio.moviemax.models.TvShowsEntity
import com.nixstudio.moviemax.models.sources.MovieMaxRepository
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ItemDetailViewModel(private val repo: MovieMaxRepository) : ViewModel() {
    private val currentMovie = MutableLiveData<MovieEntity>()
    private val currentTvShows = MutableLiveData<TvShowsEntity>()

//    0 = Movie, 1 = TvShows
    var _mode: Int = 0

    fun setCurrentMovie(id: Int) {
        val movieResponse = repo.getMovieById(id)

        movieResponse.enqueue(object: Callback<MovieEntity> {
            override fun onResponse(call: Call<MovieEntity>, response: Response<MovieEntity>) {
                if (response.isSuccessful) {
                    currentMovie.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<MovieEntity>, t: Throwable) {
                Log.e(ContentValues.TAG, "onFailure: ${t.message.toString()}")
            }
        })

        _mode = 0
    }

    fun setCurrentTvShows(id: Int) {
        val tvResponse = repo.getTvShowsById(id)

        tvResponse.enqueue(object: Callback<TvShowsEntity> {
            override fun onResponse(call: Call<TvShowsEntity>, response: Response<TvShowsEntity>) {
                if (response.isSuccessful) {
                    currentTvShows.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<TvShowsEntity>, t: Throwable) {
                Log.e(ContentValues.TAG, "onFailure: ${t.message.toString()}")
            }
        })

        _mode = 1
    }

    fun getCurrentMovie(): LiveData<MovieEntity> = currentMovie

    fun getCurrentTvShows(): LiveData<TvShowsEntity> = currentTvShows

    fun getMode(): Int = _mode
}