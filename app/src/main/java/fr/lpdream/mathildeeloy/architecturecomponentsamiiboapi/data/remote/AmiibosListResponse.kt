package fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.data.remote

import com.google.gson.annotations.SerializedName

data class AmiibosListResponse(

    @SerializedName("amiibo")
    val results: List<AmiiboResponse>

)