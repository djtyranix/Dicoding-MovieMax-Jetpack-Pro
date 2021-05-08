package com.nixstudio.moviemax.models.sources

import com.nixstudio.moviemax.models.MovieEntity
import com.nixstudio.moviemax.models.TvShowsEntity
import com.nixstudio.moviemax.models.sources.remote.*
import retrofit2.Call

class MovieMaxRepository(private val remoteDataSource: RemoteDataSource) :
    MovieMaxRepositoryInterface {
    override fun getDiscoveryMovie(page: Int): Call<DiscoverMovieResponse> {
        return remoteDataSource.getMoviesFromDiscovery(page)
    }

    override fun getDiscoveryTvShows(page: Int): Call<DiscoverTvResponse> {
        return remoteDataSource.getTvShowsFromDiscovery(page)
    }

    override fun searchByString(query: String, page: Int): Call<SearchResponse> {
        return remoteDataSource.searchFromString(query, page)
    }

    override fun getTrendingToday(): Call<TrendingResponse> {
        return remoteDataSource.getTrendingToday()
    }

    override fun getMovieById(id: Long): Call<MovieEntity> {
        return remoteDataSource.getMovieById(id)
    }

    override fun getTvShowsById(id: Long): Call<TvShowsEntity> {
        return remoteDataSource.getTvShowsById(id)
    }
}