package com.nixstudio.moviemax.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "favorite")
data class FavoriteEntity(
    @PrimaryKey val f_id: String,
    @ColumnInfo(name = "item_id") val itemId: Long,
    @ColumnInfo(name = "media_type") val mediaType: String,
) : Serializable