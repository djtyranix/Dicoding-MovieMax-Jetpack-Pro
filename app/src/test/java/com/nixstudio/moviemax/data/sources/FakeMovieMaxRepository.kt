package com.nixstudio.moviemax.data.sources

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.nixstudio.moviemax.data.entities.CombinedResultEntity
import com.nixstudio.moviemax.data.entities.FavoriteEntity
import com.nixstudio.moviemax.data.entities.MovieEntity
import com.nixstudio.moviemax.data.entities.TvShowsEntity
import com.nixstudio.moviemax.data.sources.local.LocalDataSource
import com.nixstudio.moviemax.data.sources.remote.DiscoverMovieResultsItem
import com.nixstudio.moviemax.data.sources.remote.DiscoverTvResultsItem
import com.nixstudio.moviemax.data.sources.remote.RemoteDataSource
import com.nixstudio.moviemax.data.utils.MediaType
import kotlinx.coroutines.flow.Flow

class FakeMovieMaxRepository(private val remoteDataSource: RemoteDataSource, private val localDataSource: LocalDataSource) :
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

    override fun getAllFavorites(): Flow<PagingData<FavoriteEntity>> {
        val items = Pager(
            PagingConfig(
                pageSize = 5,
                enablePlaceholders = true
            )
        ) {
            localDataSource.getAllFavorites()
        }.flow

        return items
    }

    override fun getFavoritesFromMediaType(mediaType: MediaType): Flow<PagingData<FavoriteEntity>> {
        val items = Pager(
            PagingConfig(
                pageSize = 5,
                enablePlaceholders = true
            )
        ) {
            localDataSource.getAllFromMediaType(mediaType)
        }.flow

        return items
    }

    override fun addFavorite(movie: MovieEntity?, tvShow: TvShowsEntity?) {
        var favoriteEntity: FavoriteEntity? = null

        if (movie != null) {
            favoriteEntity = movie.id?.let {
                FavoriteEntity(
                    itemId = it,
                    mediaType = "movie",
                    title = movie.title,
                    posterPath = movie.posterPath
                )
            }
        } else if (tvShow != null) {
            favoriteEntity = tvShow.id?.let {
                FavoriteEntity(
                    itemId = it,
                    mediaType = "tv",
                    title = tvShow.name,
                    posterPath = tvShow.posterPath
                )
            }
        }

        if (favoriteEntity != null) {
            localDataSource.addFavorite(favoriteEntity)
        }
    }

    override fun removeFavorite(movie: MovieEntity?, tvShow: TvShowsEntity?) {
        var favoriteEntity: FavoriteEntity? = null

        if (movie != null) {
            favoriteEntity = movie.id?.let {
                FavoriteEntity(
                    itemId = it,
                    mediaType = "movie",
                    title = movie.title,
                    posterPath = movie.posterPath
                )
            }
        } else if (tvShow != null) {
            favoriteEntity = tvShow.id?.let {
                FavoriteEntity(
                    itemId = it,
                    mediaType = "tv",
                    title = tvShow.name,
                    posterPath = tvShow.posterPath
                )
            }
        }

        if (favoriteEntity != null) {
            localDataSource.removeFavorite(favoriteEntity)
        }
    }

    override fun checkIfFavoriteExist(id: Long): Int {
        return localDataSource.checkIfRecordExist(id)
    }

    override fun getDbItemCount(): Int {
        return localDataSource.getAllCount()
    }
}