package fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.data.remote

import com.google.gson.annotations.SerializedName

data class AmiiboReleaseResponse(

    @SerializedName("au")
    val au: String,

    @SerializedName("eu")
    val eu: String,

    @SerializedName("jp")
    val jp: String,

    @SerializedName("na")
    val na: String

)