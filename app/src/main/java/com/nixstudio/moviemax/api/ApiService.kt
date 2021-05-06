package com.nixstudio.moviemax.api

import com.nixstudio.moviemax.models.sources.remote.DiscoverMovieResponse
import com.nixstudio.moviemax.models.sources.remote.DiscoverTvResponse
import com.nixstudio.moviemax.models.sources.remote.TrendingResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("discover/movie")
    fun getMovieDiscovery(
        @Path("api_key") api_key: String,
        @Path("sort_by") sort_by: String,
        @Path("include_adult") include_adult: String,
        @Path("page") page: Int
    ): Call<DiscoverMovieResponse>

    @GET("discover/tv")
    fun getTvDiscovery(
        @Path("api_key") api_key: String,
        @Path("sort_by") sort_by: String,
        @Path("include_adult") include_adult: String,
        @Path("page") page: Int
    ): Call<DiscoverTvResponse>

    @GET("trending/all/day")
    fun getTrendingToday(
        @Path("api_key") api_key: String
    ): Call<TrendingResponse>

    @GET("search/multi")
    fun searchWithString(
        @Path("api_key") api_key: String,
        @Path("query") query: String,
        @Path("page") page: Int
    )
}