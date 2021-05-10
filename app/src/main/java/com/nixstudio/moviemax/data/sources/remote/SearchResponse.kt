package com.nixstudio.moviemax.data.sources.remote

import com.google.gson.annotations.SerializedName
import com.nixstudio.moviemax.data.entities.CombinedResultEntity

data class SearchResponse(

    @field:SerializedName("page")
	val page: Int? = null,

    @field:SerializedName("total_pages")
	val totalPages: Int? = null,

    @field:SerializedName("results")
	val results: List<CombinedResultEntity?>? = null,

    @field:SerializedName("total_results")
	val totalResults: Int? = null
)
