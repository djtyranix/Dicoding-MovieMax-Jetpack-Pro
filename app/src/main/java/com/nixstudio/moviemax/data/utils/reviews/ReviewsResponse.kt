package com.nixstudio.moviemax.data.utils.reviews

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

class ReviewsResponse {
    @Parcelize
    data class Reviews(

        @field:SerializedName("page")
        val page: Int? = null,

        @field:SerializedName("total_pages")
        val totalPages: Int? = null,

        @field:SerializedName("results")
        val results: List<ReviewsItem?>? = null,

        @field:SerializedName("total_results")
        val totalResults: Int? = null
    ) : Parcelable
}