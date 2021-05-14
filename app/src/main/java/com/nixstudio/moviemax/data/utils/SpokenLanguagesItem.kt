package com.nixstudio.moviemax.data.utils

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class SpokenLanguagesItem(

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("iso_639_1")
    val iso6391: String? = null,

    @field:SerializedName("english_name")
    val englishName: String? = null
) : Parcelable
