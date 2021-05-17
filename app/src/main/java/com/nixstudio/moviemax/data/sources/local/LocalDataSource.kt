package com.nixstudio.moviemax.data.sources.local

import android.util.Log
import androidx.lifecycle.LiveData
import com.nixstudio.moviemax.data.entities.FavoriteEntity
import com.nixstudio.moviemax.data.entities.MovieEntity
import com.nixstudio.moviemax.data.entities.TvShowsEntity
import com.nixstudio.moviemax.data.sources.local.room.MovieMaxDao
import com.nixstudio.moviemax.data.utils.MediaType

class LocalDataSource(private val movieMaxDao: MovieMaxDao) {

    fun getAllFavorites(): LiveData<List<FavoriteEntity>> = movieMaxDao.getAll()

    fun getAllFromMediaType(mediaType: MediaType): LiveData<List<FavoriteEntity>> {
        return if (mediaType == MediaType.MOVIE) {
            movieMaxDao.getAllFromMediaType("movie")
        } else {
            movieMaxDao.getAllFromMediaType("tv")
        }
    }

    fun getAllFromTitle(title: String): LiveData<List<FavoriteEntity>> = movieMaxDao.getAllFromTitle(title)

    fun addFavorite(favoriteEntity: FavoriteEntity) {
        try {
            Log.d("MASUK", "SINI")
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
}