package com.nixstudio.moviemax.models.sources

import android.content.ContentValues.TAG
import android.util.Log
import com.nixstudio.moviemax.models.CombinedResultEntity
import com.nixstudio.moviemax.models.MovieEntity
import com.nixstudio.moviemax.models.TvShowsEntity
import com.nixstudio.moviemax.models.sources.remote.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieMaxRepository(private val remoteDataSource: RemoteDataSource) :
    DataSourceInterface {
    override fun getDiscoveryMovie(): Call<DiscoverMovieResponse> {
        return remoteDataSource.getMoviesFromDiscovery()
    }

    override fun getDiscoveryTvShows(): Call<DiscoverTvResponse> {
        return remoteDataSource.getTvShowsFromDiscovery()
    }

    override fun searchByString(query: String): Call<SearchResponse> {
        return remoteDataSource.searchFromString(query)
    }

    override fun getTrendingToday(): Call<TrendingResponse> {
        return remoteDataSource.getTrendingToday()
    }

    override fun getMovieById(id: Int): Call<MovieEntity> {
        return remoteDataSource.getMovieById(id)
    }

    override fun getTvShowsById(id: Int): Call<TvShowsEntity> {
        return remoteDataSource.getTvShowsById(id)
    }
}