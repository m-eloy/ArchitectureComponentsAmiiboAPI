package fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.data.remote

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.Observable
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface AmiiboService {

    @GET("amiibo/")
    fun getAllAmiibos(): Observable<AmiibosListResponse>

    @GET("amiibo/?name={amiibo}")
    fun getAmiibo(@Path("amiibo") name: String): Observable<AmiiboResponse>

    companion object {

        fun create(): AmiiboService {

            val client = OkHttpClient.Builder().addInterceptor { chain ->
                val request = chain.request()
                val newUrl = request.url().newBuilder().build()
                chain.proceed(request.newBuilder().url(newUrl).build())
            }.build()


            val retrofit = Retrofit.Builder()
                .baseUrl("https://www.amiiboapi.com/api/")
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(AmiiboService::class.java)
        }
    }
}