package fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.ui.amiibo.list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.data.model.Amiibo
import fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.data.AmiiboRepository
import fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.data.remote.AmiibosResponseCallback
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class AmiibosViewModel(application: Application) : AndroidViewModel(application), KoinComponent {

    private val amiiboRepository: AmiiboRepository by inject()

    var amiibos: LiveData<List<Amiibo>> = amiiboRepository.getAll()

    fun delete(amiibo: Amiibo) {
        amiiboRepository.delete(amiibo)
    }

    fun refresh(callback: AmiibosResponseCallback) {
        amiiboRepository.downloadAmiibos(callback)
    }
}