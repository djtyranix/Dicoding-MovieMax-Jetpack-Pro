package com.nixstudio.moviemax.data.utils

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CreatedByItem(

    @field:SerializedName("gender")
    val gender: Int? = null,

    @field:SerializedName("credit_id")
    val creditId: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("profile_path")
    val profilePath: String? = null,

    @field:SerializedName("id")
    val id: Int? = null
) : Parcelable
