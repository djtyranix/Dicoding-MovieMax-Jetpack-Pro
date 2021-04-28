package com.nixstudio.moviemax.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieEntity(
    var movieId: Int,
    var movieTitle: String,
    var moviePoster: String,
    var movieYear: Int,
    var genre: String,
    var overview: String?,
    var playtime: String?
) : Parcelable
