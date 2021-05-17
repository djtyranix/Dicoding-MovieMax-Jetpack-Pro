package com.nixstudio.moviemax.data.sources.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.nixstudio.moviemax.data.entities.FavoriteEntity

@Dao
interface MovieMaxDao {
    @Query("SELECT * FROM favorite")
    fun getAll(): LiveData<List<FavoriteEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(favorite: FavoriteEntity)

    @Delete
    fun delete(favorite: FavoriteEntity)

    @Query("SELECT * FROM favorite WHERE media_type = :mediaType")
    fun getAllFromMediaType(mediaType: String): LiveData<List<FavoriteEntity>>

    @Query("SELECT * FROM favorite WHERE title = :title OR name = :title")
    fun getAllFromTitle(title: String): LiveData<List<FavoriteEntity>>

    @Query("SELECT COUNT(item_id) FROM favorite WHERE item_id = :id")
    fun checkIfRecordExist(id: Long): Int
}