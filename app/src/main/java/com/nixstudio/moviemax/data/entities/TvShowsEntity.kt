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

    @field:SerializedName("original_language")
    val originalLanguage: String? = null,

    @field:SerializedName("number_of_episodes")
    val numberOfEpisodes: Int? = null,

    @field:SerializedName("networks")
    val networks: List<NetworksItem?>? = null,

    @field:SerializedName("type")
    val type: String? = null,

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

    @field:SerializedName("production_countries")
    val productionCountries: List<ProductionCountriesItem?>? = null,

    @field:SerializedName("id")
    val id: Long? = null,

    @field:SerializedName("number_of_seasons")
    val numberOfSeasons: Int? = null,

    @field:SerializedName("vote_count")
    val voteCount: Long? = null,

    @field:SerializedName("first_air_date")
    val firstAirDate: String? = null,

    @field:SerializedName("overview")
    val overview: String? = null,

    @field:SerializedName("seasons")
    val seasons: List<SeasonsItem?>? = null,

    @field:SerializedName("languages")
    val languages: List<String?>? = null,

    @field:SerializedName("created_by")
    val createdBy: List<CreatedByItem?>? = null,

    @field:SerializedName("last_episode_to_air")
    val lastEpisodeToAir: LastEpisodeToAir? = null,

    @field:SerializedName("poster_path")
    val posterPath: String? = null,

    @field:SerializedName("origin_country")
    val originCountry: List<String?>? = null,

    @field:SerializedName("spoken_languages")
    val spokenLanguages: List<SpokenLanguagesItem?>? = null,

    @field:SerializedName("production_companies")
    val productionCompanies: List<ProductionCompaniesItem?>? = null,

    @field:SerializedName("original_name")
    val originalName: String? = null,

    @field:SerializedName("vote_average")
    val voteAverage: Double? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("tagline")
    val tagline: String? = null,

    @field:SerializedName("episode_run_time")
    val episodeRunTime: List<Int?>? = null,

    @field:SerializedName("in_production")
    val inProduction: Boolean? = null,

    @field:SerializedName("last_air_date")
    val lastAirDate: String? = null,

    @field:SerializedName("homepage")
    val homepage: String? = null,

    @field:SerializedName("status")
    val status: String? = null
) : Parcelable
