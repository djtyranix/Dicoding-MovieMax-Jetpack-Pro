package com.nixstudio.moviemax.models.sources

import com.nixstudio.moviemax.models.MovieEntity
import com.nixstudio.moviemax.models.TvShowsEntity

interface MainDataSource {
    fun getMoviesFromDiscovery(): List<MovieEntity>

    fun getTvShowsFromDiscovery(): List<TvShowsEntity>

    fun searchFromString(string: String)
}