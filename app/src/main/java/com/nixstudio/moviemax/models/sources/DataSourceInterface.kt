package com.nixstudio.moviemax.models.sources

import com.nixstudio.moviemax.models.CombinedResultEntity
import com.nixstudio.moviemax.models.MovieEntity
import com.nixstudio.moviemax.models.TvShowsEntity
import com.nixstudio.moviemax.models.sources.remote.*
import retrofit2.Call

interface DataSourceInterface {
    fun getDiscoveryMovie(): Call<DiscoverMovieResponse>

    fun getDiscoveryTvShows(): Call<DiscoverTvResponse>

    fun searchByString(query: String): Call<SearchResponse>

    fun getTrendingToday(): Call<TrendingResponse>

    fun getMovieById(id: Int): Call<MovieEntity>

    fun getTvShowsById(id: Int): Call<TvShowsEntity>
}