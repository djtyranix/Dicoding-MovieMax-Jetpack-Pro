package com.nixstudio.moviemax.data.sources.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.nixstudio.moviemax.data.entities.FavoriteEntity

@Dao
interface MovieMaxDao {
    @Query("SELECT * FROM favorite")
    fun getAll(): LiveData<List<FavoriteEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(favorite: List<FavoriteEntity>)

    @Delete
    fun delete(favorite: FavoriteEntity)
}