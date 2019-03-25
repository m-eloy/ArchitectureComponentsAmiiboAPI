package fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.ui.amiibo.create

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.data.locale.Amiibo
import fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.data.locale.AmiiboRepository
import java.util.*

class CreateAmiiboViewModel(application: Application): AndroidViewModel(application) {

    var amiiboSeries: MutableLiveData<String> = MutableLiveData()

    var character: MutableLiveData<String> = MutableLiveData()

    var gameSeries: MutableLiveData<String> = MutableLiveData()

    var release: MutableLiveData<Date> = MutableLiveData()

    fun insert() {
        AmiiboRepository.insert(
            Amiibo(
                amiiboSeries = amiiboSeries.value?.capitalize() ?: "",
                character = character.value?.capitalize() ?: "",
                gameSeries = gameSeries.value?.capitalize() ?: "",
                release = release.value ?: Date()
            )
        )
    }
}