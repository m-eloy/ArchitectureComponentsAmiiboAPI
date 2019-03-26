package fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.data

import android.util.Log
import androidx.lifecycle.LiveData
import fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.data.locale.AmiiboDao
import fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.data.model.Amiibo
import fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.data.remote.AmiiboReleaseResponse
import fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.data.remote.AmiiboResponse
import fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.data.remote.AmiiboService
import fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.data.remote.AmiibosResponseCallback
import fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.extension.toDate
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.doAsync
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import java.util.*

class AmiiboRepository: KoinComponent {

    private val amiiboDao: AmiiboDao by inject()

    private val service = AmiiboService.create()

    //region locale

    fun insertAll(amiibos: List<Amiibo>) = doAsync {
        amiiboDao.insertAll(amiibos.distinctBy { amiibo -> amiibo.character })
        Log.d("amiiboRepository", "inserting amiibo: $amiibos")
    }

    fun insert(amiibo: Amiibo) = insertAll(listOf(amiibo))

    fun delete(amiibo: Amiibo) = doAsync { amiiboDao.delete(amiibo) }

    fun getById(id: Int): LiveData<Amiibo> = amiiboDao.getById(id)

    fun getAll(): LiveData<List<Amiibo>> = amiiboDao.getAllLive()

    //endregion

    //region remote

    fun downloadAmiibos(callback: AmiibosResponseCallback) {
        service.getAllAmiibos()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onNext = { amiibosListResponse ->
                    insertAll(
                        amiibosListResponse.results.map { amiiboResponse ->
                            amiiboResponseToAmiibo(amiiboResponse)
                        })
                },
                onComplete = { callback.onSuccess() },
                onError = { callback.onError(it) }
            )
    }

    fun downloadAmiibosWithExtraInfos(callback: AmiibosResponseCallback) {
        service.getAllAmiibos()
            .subscribeOn(Schedulers.io())
            .flatMap { amiiboListResponse -> Observable.fromIterable(amiiboListResponse.results) }
            .flatMap { amiiboShortResponse -> service.getAmiibo(amiiboShortResponse.name) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onNext = { amiiboFullResponse -> insert(amiiboResponseToAmiibo(amiiboFullResponse)) },
                onComplete = { callback.onSuccess() },
                onError = { callback.onError(it) }
            )
    }

    private fun amiiboResponseToAmiibo(amiiboResponse: AmiiboResponse): Amiibo =
        Amiibo(
            id = (0..100).random(),
            amiiboSeries = amiiboResponse.amiiboSeries,
            character = amiiboResponse.character,
            gameSeries = amiiboResponse.gameSeries,
            release = getReleaseDate(
                amiiboResponse.release
            ).toDate("yyyy-MM-dd") ?: Date(),
            imageUrl = amiiboResponse.image
        )

    private fun getReleaseDate(release: AmiiboReleaseResponse): String {
        val res: String
        if (release.eu != null ) {
            res = release.eu
        } else {
            res = "0000-00-00"
        }
        return res
    }

    //endregion
}