package com.nixstudio.moviemax.data.entities

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.nixstudio.moviemax.data.utils.GenresItem
import com.nixstudio.moviemax.data.utils.credits.Credits
import com.nixstudio.moviemax.data.utils.reviews.ReviewsResponse
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieEntity(

    @field:SerializedName("id")
    val id: Long?,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("backdrop_path")
    val backdropPath: String? = null,

    @field:SerializedName("reviews")
    val reviews: ReviewsResponse.Reviews? = null,

    @field:SerializedName("credits")
    val credits: Credits? = null,

    @field:SerializedName("genres")
    val genres: List<GenresItem?>? = null,

    @field:SerializedName("popularity")
    val popularity: Double? = null,

    @field:SerializedName("overview")
    val overview: String? = null,

    @field:SerializedName("runtime")
    val runtime: Int? = null,

    @field:SerializedName("poster_path")
    val posterPath: String? = null,

    @field:SerializedName("release_date")
    val releaseDate: String? = null,

    @field:SerializedName("vote_average")
    val voteAverage: Double? = null,

    @field:SerializedName("adult")
    val adult: Boolean? = null,

    @field:SerializedName("status")
    val status: String? = null
) : Parcelable
