package fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.data.remote

interface AmiibosResponseCallback {

    fun onSuccess()

    fun onError(throwable: Throwable)
}