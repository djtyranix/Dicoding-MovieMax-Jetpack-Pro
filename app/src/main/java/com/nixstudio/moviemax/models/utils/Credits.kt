package com.nixstudio.moviemax.models.utils

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Credits(

    @field:SerializedName("cast")
    val cast: List<CastItem?>? = null,

    @field:SerializedName("crew")
    val crew: List<CrewItem?>? = null
) : Parcelable
