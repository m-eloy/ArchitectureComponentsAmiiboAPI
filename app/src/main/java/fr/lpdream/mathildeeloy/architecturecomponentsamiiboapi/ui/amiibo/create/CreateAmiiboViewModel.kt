package fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.ui.amiibo.create

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.data.model.Amiibo
import fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.data.AmiiboRepository
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import java.util.*

class CreateAmiiboViewModel(application: Application): AndroidViewModel(application), KoinComponent {

    private val amiiboRepository: AmiiboRepository by inject()

    var amiiboSeries: MutableLiveData<String> = MutableLiveData()

    var character: MutableLiveData<String> = MutableLiveData()

    var gameSeries: MutableLiveData<String> = MutableLiveData()

    var release: MutableLiveData<Date> = MutableLiveData()

    fun insert() {
        amiiboRepository.insert(
            Amiibo(
                amiiboSeries = amiiboSeries.value?.capitalize() ?: "",
                character = character.value?.capitalize() ?: "",
                gameSeries = gameSeries.value?.capitalize() ?: "",
                release = release.value ?: Date()
            )
        )
    }
}