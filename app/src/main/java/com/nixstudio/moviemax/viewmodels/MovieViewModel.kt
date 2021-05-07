package com.nixstudio.moviemax.viewmodels

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nixstudio.moviemax.models.MovieEntity
import com.nixstudio.moviemax.models.sources.MovieMaxRepository
import com.nixstudio.moviemax.models.sources.remote.DiscoverMovieResponse
import com.nixstudio.moviemax.models.sources.remote.DiscoverMovieResultsItem
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieViewModel(private val repo: MovieMaxRepository) : ViewModel() {
    private val listMovie = MutableLiveData<List<DiscoverMovieResultsItem>>()

    fun setMovies() {
        val discoverMovieResponse = repo.getDiscoveryMovie()

        discoverMovieResponse.enqueue(object : Callback<DiscoverMovieResponse> {
            override fun onResponse(
                call: Call<DiscoverMovieResponse>,
                response: Response<DiscoverMovieResponse>
            ) {
                if (response.isSuccessful) {
                    listMovie.postValue(response.body()?.results as ArrayList<DiscoverMovieResultsItem>)
                }
            }

            override fun onFailure(call: Call<DiscoverMovieResponse>, t: Throwable) {
                Log.e(ContentValues.TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun getMovies(): LiveData<List<DiscoverMovieResultsItem>> = listMovie
}