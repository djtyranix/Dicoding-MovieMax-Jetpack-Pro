package com.nixstudio.moviemax.models.sources

import com.nixstudio.moviemax.models.CombinedResultEntity
import com.nixstudio.moviemax.models.MovieEntity
import com.nixstudio.moviemax.models.TvShowsEntity
import com.nixstudio.moviemax.models.sources.remote.*
import retrofit2.Call

interface MovieMaxRepositoryInterface {
    fun getDiscoveryMovie(page: Int = 1): Call<DiscoverMovieResponse>

    fun getDiscoveryTvShows(page: Int = 1): Call<DiscoverTvResponse>

    fun searchByString(query: String, page: Int = 1): Call<SearchResponse>

    fun getTrendingToday(): Call<TrendingResponse>

    fun getMovieById(id: Long): Call<MovieEntity>

    fun getTvShowsById(id: Long): Call<TvShowsEntity>
}