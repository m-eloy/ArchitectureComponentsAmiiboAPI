package fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.data.remote

import com.google.gson.annotations.SerializedName

data class AmiiboResponse(

    @SerializedName("amiiboSeries")
    val amiiboSeries: String,

    @SerializedName("character")
    val character: String,

    @SerializedName("gameSeries")
    val gameSeries: String,

    @SerializedName("head")
    val head: String,

    @SerializedName("image")
    val image: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("release")
    val release: AmiiboReleaseResponse,

    @SerializedName("tail")
    val tail: String,

    @SerializedName("type")
    val type: String

)