package com.nixstudio.moviemax.data.entities

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.nixstudio.moviemax.data.utils.*
import com.nixstudio.moviemax.data.utils.credits.Credits
import com.nixstudio.moviemax.data.utils.reviews.ReviewsResponse
import com.nixstudio.moviemax.data.utils.tvshows.LastEpisodeToAir
import com.nixstudio.moviemax.data.utils.tvshows.SeasonsItem
import kotlinx.parcelize.Parcelize

@Parcelize
data class TvShowsEntity(

    @field:SerializedName("id")
    val id: Long?,

    @field:SerializedName("number_of_episodes")
    val numberOfEpisodes: Int? = null,

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

    @field:SerializedName("number_of_seasons")
    val numberOfSeasons: Int? = null,

    @field:SerializedName("first_air_date")
    val firstAirDate: String? = null,

    @field:SerializedName("overview")
    val overview: String? = null,

    @field:SerializedName("seasons")
    val seasons: List<SeasonsItem?>? = null,

    @field:SerializedName("last_episode_to_air")
    val lastEpisodeToAir: LastEpisodeToAir? = null,

    @field:SerializedName("poster_path")
    val posterPath: String? = null,

    @field:SerializedName("vote_average")
    val voteAverage: Double? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("episode_run_time")
    val episodeRunTime: List<Int?>? = null,

    @field:SerializedName("last_air_date")
    val lastAirDate: String? = null,

    @field:SerializedName("status")
    val status: String? = null
) : Parcelable
