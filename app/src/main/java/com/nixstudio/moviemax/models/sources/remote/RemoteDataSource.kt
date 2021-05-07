package com.nixstudio.moviemax.models.sources.remote

import com.nixstudio.moviemax.BuildConfig
import com.nixstudio.moviemax.api.ApiService
import com.nixstudio.moviemax.models.MovieEntity
import com.nixstudio.moviemax.models.TvShowsEntity

class RemoteDataSource(private val api: ApiService) {
    private val api_key = BuildConfig.API_KEY
    private val include_adult = false
    private val sort_by = "popularity.desc"
    private val page = 1

    fun getMoviesFromDiscovery() = api.getMovieDiscovery(api_key, sort_by, include_adult, page)

    fun getTvShowsFromDiscovery() = api.getTvDiscovery(api_key, sort_by, include_adult, page)

    fun searchFromString(string: String) = api.searchWithString(api_key, string, page)

    fun getTrendingToday() = api.getTrendingToday(api_key)

    fun getMovieById(id: Int) = api.getMovieById(id, api_key)

    fun getTvShowsById(id: Int) = api.getTvShowsById(id, api_key)
}