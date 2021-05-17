package com.nixstudio.moviemax.data.sources

import androidx.lifecycle.LiveData
import com.nixstudio.moviemax.data.entities.CombinedResultEntity
import com.nixstudio.moviemax.data.entities.FavoriteEntity
import com.nixstudio.moviemax.data.entities.MovieEntity
import com.nixstudio.moviemax.data.entities.TvShowsEntity
import com.nixstudio.moviemax.data.sources.remote.DiscoverMovieResultsItem
import com.nixstudio.moviemax.data.sources.remote.DiscoverTvResultsItem
import com.nixstudio.moviemax.data.utils.MediaType

interface MovieMaxRepositoryInterface {
    fun getDiscoveryMovie(page: Int = 1): LiveData<List<DiscoverMovieResultsItem>>

    fun getDiscoveryTvShows(page: Int = 1): LiveData<List<DiscoverTvResultsItem>>

    fun searchByString(query: String, page: Int = 1): LiveData<List<CombinedResultEntity>>

    fun getTrendingToday(): LiveData<List<CombinedResultEntity>>

    fun getMovieById(id: Long): LiveData<MovieEntity>

    fun getTvShowsById(id: Long): LiveData<TvShowsEntity>

    fun getAllFavorites(): LiveData<List<FavoriteEntity>>

    fun getFavoritesFromMediaType(mediaType: MediaType): LiveData<List<FavoriteEntity>>

    fun getFavoritesFromTitle(title: String): LiveData<List<FavoriteEntity>>

    fun addFavorite(movie: MovieEntity? = null, tvShow: TvShowsEntity? = null)

    fun removeFavorite(movie: MovieEntity? = null, tvShow: TvShowsEntity? = null)

    fun checkIfFavoriteExist(id: Long): Int
}