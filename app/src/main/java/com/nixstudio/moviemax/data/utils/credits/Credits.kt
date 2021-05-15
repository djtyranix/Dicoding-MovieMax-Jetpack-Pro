package com.nixstudio.moviemax.data.utils.credits

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Credits(

    @field:SerializedName("cast")
    val cast: List<CastItem?>? = null,

    @field:SerializedName("crew")
    val crew: List<CrewItem?>? = null
) : Parcelable
