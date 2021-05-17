package com.nixstudio.moviemax.data.sources.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nixstudio.moviemax.data.entities.FavoriteEntity

@Database(entities = [FavoriteEntity::class], version = 1, exportSchema = false)
abstract class MovieMaxDatabase : RoomDatabase() {
    abstract fun movieMaxDao(): MovieMaxDao
}