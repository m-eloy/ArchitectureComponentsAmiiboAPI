package fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.ui.amiibo.create

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.data.Amiibo
import fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.data.AmiiboRepository
import java.util.*

class CreateAmiiboViewModel(application: Application): AndroidViewModel(application) {

    var amiiboSeries: String = ""

    var character: String = ""

    var gameSeries: String = ""

    var release: Date = Date()

    fun insert() {
        AmiiboRepository.insert(Amiibo(amiiboSeries = amiiboSeries, character = character, gameSeries = gameSeries, release = release))
    }
}