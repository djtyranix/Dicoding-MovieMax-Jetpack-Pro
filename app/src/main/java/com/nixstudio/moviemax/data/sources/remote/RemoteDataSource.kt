package com.nixstudio.moviemax.data.sources.remote

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nixstudio.moviemax.BuildConfig
import com.nixstudio.moviemax.data.entities.CombinedResultEntity
import com.nixstudio.moviemax.data.entities.MovieEntity
import com.nixstudio.moviemax.data.entities.TvShowsEntity
import com.nixstudio.moviemax.data.sources.remote.api.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource(private val api: ApiService) {
    private val apiKey = BuildConfig.API_KEY
    private val includeAdult = true
    private val sortBy = "popularity.desc"
    private val appendToResponse = "credits,reviews"

    fun getMoviesFromDiscovery(page: Int): LiveData<List<DiscoverMovieResultsItem>> {
        val discoverMovieResponse = api.getMovieDiscovery(apiKey, sortBy, includeAdult, page)
        val discoveryMovies = MutableLiveData<List<DiscoverMovieResultsItem>>()

        discoverMovieResponse.enqueue(object : Callback<DiscoverMovieResponse> {
            override fun onResponse(
                call: Call<DiscoverMovieResponse>,
                response: Response<DiscoverMovieResponse>
            ) {
                if (response.isSuccessful) {
                    discoveryMovies.postValue(response.body()?.results as ArrayList<DiscoverMovieResultsItem>)
                }
            }

            override fun onFailure(call: Call<DiscoverMovieResponse>, t: Throwable) {
                Log.e(ContentValues.TAG, "onFailure: ${t.message.toString()}")
            }
        })

        return discoveryMovies
    }

    fun getTvShowsFromDiscovery(page: Int): LiveData<List<DiscoverTvResultsItem>> {
        val discoverTvResponse = api.getTvDiscovery(apiKey, sortBy, includeAdult, page)
        val discoveryTvShows = MutableLiveData<List<DiscoverTvResultsItem>>()

        discoverTvResponse.enqueue(object : Callback<DiscoverTvResponse> {
            override fun onResponse(
                call: Call<DiscoverTvResponse>,
                response: Response<DiscoverTvResponse>
            ) {
                if (response.isSuccessful) {
                    discoveryTvShows.postValue(response.body()?.results as ArrayList<DiscoverTvResultsItem>)
                }
            }

            override fun onFailure(call: Call<DiscoverTvResponse>, t: Throwable) {
                Log.e(ContentValues.TAG, "onFailure: ${t.message.toString()}")
            }
        })

        return discoveryTvShows
    }

    fun searchFromString(string: String, page: Int): LiveData<List<CombinedResultEntity>> {
        val searchResponse = api.searchWithString(apiKey, string, page)
        val searchList = MutableLiveData<List<CombinedResultEntity>>()

        searchResponse.enqueue(object : Callback<SearchResponse> {
            override fun onResponse(
                call: Call<SearchResponse>,
                response: Response<SearchResponse>
            ) {
                if (response.isSuccessful) {
                    val result = response.body()?.results
                    searchList.postValue(result?.filter { it?.mediaType == "movie" || it?.mediaType == "tv" } as ArrayList<CombinedResultEntity>)
                }
            }

            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                Log.e(ContentValues.TAG, "onFailure: ${t.message.toString()}")
            }
        })

        return searchList
    }

    fun getTrendingToday(): LiveData<List<CombinedResultEntity>> {
        val trendingResponse = api.getTrendingToday(apiKey)
        val trendingList = MutableLiveData<List<CombinedResultEntity>>()

        trendingResponse.enqueue(object : Callback<TrendingResponse> {
            override fun onResponse(
                call: Call<TrendingResponse>,
                response: Response<TrendingResponse>
            ) {
                if (response.isSuccessful) {
                    trendingList.postValue(response.body()?.results as ArrayList<CombinedResultEntity>)
                }
            }

            override fun onFailure(call: Call<TrendingResponse>, t: Throwable) {
                Log.e(ContentValues.TAG, "onFailure: ${t.message.toString()}")
            }
        })

        return trendingList
    }

    fun getMovieById(id: Long): LiveData<MovieEntity> {
        val movieResponse = api.getMovieById(id, apiKey, appendToResponse)
        val currentMovie = MutableLiveData<MovieEntity>()

        movieResponse.enqueue(object : Callback<MovieEntity> {
            override fun onResponse(call: Call<MovieEntity>, response: Response<MovieEntity>) {
                if (response.isSuccessful) {
                    currentMovie.postValue(response.body() as MovieEntity)
                }
            }

            override fun onFailure(call: Call<MovieEntity>, t: Throwable) {
                Log.e(ContentValues.TAG, "onFailure: ${t.message.toString()}")
            }
        })

        return currentMovie
    }

    fun getTvShowsById(id: Long): LiveData<TvShowsEntity> {
        val tvResponse = api.getTvShowsById(id, apiKey, appendToResponse)
        val currentTvShows = MutableLiveData<TvShowsEntity>()

        tvResponse.enqueue(object : Callback<TvShowsEntity> {
            override fun onResponse(call: Call<TvShowsEntity>, response: Response<TvShowsEntity>) {
                if (response.isSuccessful) {
                    currentTvShows.postValue(response.body() as TvShowsEntity)
                }
            }

            override fun onFailure(call: Call<TvShowsEntity>, t: Throwable) {
                Log.e(ContentValues.TAG, "onFailure: ${t.message.toString()}")
            }
        })

        return currentTvShows
    }
}