package com.nixstudio.moviemax.viewmodels

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nixstudio.moviemax.models.CombinedResultEntity
import com.nixstudio.moviemax.models.MovieEntity
import com.nixstudio.moviemax.models.TvShowsEntity
import com.nixstudio.moviemax.models.sources.MovieMaxRepository
import com.nixstudio.moviemax.models.sources.remote.*
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel(private val repo: MovieMaxRepository) : ViewModel() {
    private val _listMovie = MutableLiveData<List<DiscoverMovieResultsItem>>()
    private val _listTv = MutableLiveData<List<DiscoverTvResultsItem>>()
    private val _listTrending = MutableLiveData<List<CombinedResultEntity>>()
    private val _isLoading = MutableLiveData<Boolean>()

    fun setMovies() {
        val discoverMovieResponse = repo.getDiscoveryMovie()

        discoverMovieResponse.enqueue(object : Callback<DiscoverMovieResponse> {
            override fun onResponse(
                call: Call<DiscoverMovieResponse>,
                response: Response<DiscoverMovieResponse>
            ) {
                if (response.isSuccessful) {
                    _listMovie.postValue(response.body()?.results?.take(7) as ArrayList<DiscoverMovieResultsItem>)
                }
            }

            override fun onFailure(call: Call<DiscoverMovieResponse>, t: Throwable) {
                Log.e(ContentValues.TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun setTvShows() {
        val discoverTvResponse = repo.getDiscoveryTvShows()

        discoverTvResponse.enqueue(object : Callback<DiscoverTvResponse> {
            override fun onResponse(
                call: Call<DiscoverTvResponse>,
                response: Response<DiscoverTvResponse>
            ) {
                if (response.isSuccessful) {
                    _listTv.postValue(response.body()?.results?.take(7) as ArrayList<DiscoverTvResultsItem>)
                }
            }

            override fun onFailure(call: Call<DiscoverTvResponse>, t: Throwable) {
                Log.e(ContentValues.TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun setTrending() {
        val trendingResponse = repo.getTrendingToday()

        trendingResponse.enqueue(object : Callback<TrendingResponse> {
            override fun onResponse(
                call: Call<TrendingResponse>,
                response: Response<TrendingResponse>
            ) {
                if (response.isSuccessful) {
                    _listTrending.postValue(response.body()?.results?.take(7) as ArrayList<CombinedResultEntity>)
                }
            }

            override fun onFailure(call: Call<TrendingResponse>, t: Throwable) {
                Log.e(ContentValues.TAG, "onFailure: ${t.message.toString()}")
            }

        })
    }

    fun setLoadingState(state: Boolean) {
        _isLoading.value = state
    }

    fun getTvShows(): LiveData<List<DiscoverTvResultsItem>> = _listTv

    fun getMovies(): LiveData<List<DiscoverMovieResultsItem>> = _listMovie

    fun getTrending(): LiveData<List<CombinedResultEntity>> = _listTrending

    fun getLoadingState(): LiveData<Boolean> = _isLoading
}