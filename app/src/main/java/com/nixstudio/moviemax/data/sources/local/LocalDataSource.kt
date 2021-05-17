package com.nixstudio.moviemax.data.sources.local

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.PagingSource
import com.nixstudio.moviemax.data.entities.FavoriteEntity
import com.nixstudio.moviemax.data.entities.MovieEntity
import com.nixstudio.moviemax.data.entities.TvShowsEntity
import com.nixstudio.moviemax.data.sources.local.room.MovieMaxDao
import com.nixstudio.moviemax.data.utils.MediaType

class LocalDataSource(private val movieMaxDao: MovieMaxDao) {

    fun getAllFavorites(): PagingSource<Int, FavoriteEntity> = movieMaxDao.getAll()

    fun getAllFromMediaType(mediaType: MediaType): PagingSource<Int, FavoriteEntity> {
        return if (mediaType == MediaType.MOVIE) {
            movieMaxDao.getAllFromMediaType("movie")
        } else {
            movieMaxDao.getAllFromMediaType("tv")
        }
    }

    fun addFavorite(favoriteEntity: FavoriteEntity) {
        try {
            movieMaxDao.insert(favoriteEntity)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }

    fun removeFavorite(favoriteEntity: FavoriteEntity) {
        try {
            movieMaxDao.delete(favoriteEntity)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }

    fun checkIfRecordExist(id: Long): Int = movieMaxDao.checkIfRecordExist(id)

    fun getAllCount(): Int = movieMaxDao.getItemCount()
}