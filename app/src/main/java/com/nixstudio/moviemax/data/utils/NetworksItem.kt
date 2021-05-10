package com.nixstudio.moviemax.data.utils

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NetworksItem(

    @field:SerializedName("logo_path")
    val logoPath: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("origin_country")
    val originCountry: String? = null
) : Parcelable
