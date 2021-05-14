package com.nixstudio.moviemax.data.utils

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductionCountriesItem(

    @field:SerializedName("iso_3166_1")
    val iso31661: String? = null,

    @field:SerializedName("name")
    val name: String? = null
) : Parcelable
