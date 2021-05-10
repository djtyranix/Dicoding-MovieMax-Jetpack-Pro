package com.nixstudio.moviemax.data.sources

import androidx.lifecycle.LiveData
import com.nixstudio.moviemax.data.entities.CombinedResultEntity
import com.nixstudio.moviemax.data.entities.MovieEntity
import com.nixstudio.moviemax.data.entities.TvShowsEntity
import com.nixstudio.moviemax.data.sources.MovieMaxRepositoryInterface
import com.nixstudio.moviemax.data.sources.remote.DiscoverMovieResultsItem
import com.nixstudio.moviemax.data.sources.remote.DiscoverTvResultsItem
import com.nixstudio.moviemax.data.sources.remote.RemoteDataSource

class FakeMovieMaxRepository(private val remoteDataSource: RemoteDataSource) :
    MovieMaxRepositoryInterface {

    override fun getDiscoveryMovie(page: Int): LiveData<List<DiscoverMovieResultsItem>> {
        return remoteDataSource.getMoviesFromDiscovery(page)
    }

    override fun getDiscoveryTvShows(page: Int): LiveData<List<DiscoverTvResultsItem>> {
        return remoteDataSource.getTvShowsFromDiscovery(page)
    }

    override fun searchByString(query: String, page: Int): LiveData<List<CombinedResultEntity>> {
        return remoteDataSource.searchFromString(query, page)
    }

    override fun getTrendingToday(): LiveData<List<CombinedResultEntity>> {
        return remoteDataSource.getTrendingToday()
    }

    override fun getMovieById(id: Long): LiveData<MovieEntity> {
        return remoteDataSource.getMovieById(id)
    }

    override fun getTvShowsById(id: Long): LiveData<TvShowsEntity> {
        return remoteDataSource.getTvShowsById(id)
    }
}