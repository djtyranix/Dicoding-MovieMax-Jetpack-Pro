package com.nixstudio.moviemax.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TvShowsEntity(
    var tvId: Int,
    var tvTitle: String,
    var tvPoster: String?,
    var tvYear: Int,
    var genre: String,
    var season: Int,
    var overview: String?
) : Parcelable
