package com.nixstudio.moviemax.api

import com.nixstudio.moviemax.data.entities.MovieEntity
import com.nixstudio.moviemax.data.entities.TvShowsEntity
import com.nixstudio.moviemax.data.sources.remote.DiscoverMovieResponse
import com.nixstudio.moviemax.data.sources.remote.DiscoverTvResponse
import com.nixstudio.moviemax.data.sources.remote.SearchResponse
import com.nixstudio.moviemax.data.sources.remote.TrendingResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("discover/movie")
    fun getMovieDiscovery(
        @Query("api_key") api_key: String,
        @Query("sort_by") sort_by: String,
        @Query("include_adult") include_adult: Boolean,
        @Query("page") page: Int
    ): Call<DiscoverMovieResponse>

    @GET("discover/tv")
    fun getTvDiscovery(
        @Query("api_key") api_key: String,
        @Query("sort_by") sort_by: String,
        @Query("include_adult") include_adult: Boolean,
        @Query("page") page: Int
    ): Call<DiscoverTvResponse>

    @GET("trending/all/day")
    fun getTrendingToday(
        @Query("api_key") api_key: String
    ): Call<TrendingResponse>

    @GET("search/multi")
    fun searchWithString(
        @Query("api_key") api_key: String,
        @Query("query") query: String,
        @Query("page") page: Int
    ): Call<SearchResponse>

    @GET("movie/{id}")
    fun getMovieById(
        @Path("id") id: Long,
        @Query("api_key") api_key: String
    ): Call<MovieEntity>

    @GET("tv/{id}")
    fun getTvShowsById(
        @Path("id") id: Long,
        @Query("api_key") api_key: String
    ): Call<TvShowsEntity>
}