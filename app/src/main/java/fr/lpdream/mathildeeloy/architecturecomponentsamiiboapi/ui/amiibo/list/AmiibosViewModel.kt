package fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.ui.amiibo.list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.data.locale.Amiibo
import fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.data.locale.AmiiboRepository
import fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.data.remote.AmiibosResponseCallback

class AmiibosViewModel(application: Application) : AndroidViewModel(application) {

    var amiibos: LiveData<List<Amiibo>> = AmiiboRepository.getAll()

    fun delete(amiibo: Amiibo) {
        AmiiboRepository.delete(amiibo)
    }

    fun refresh(callback: AmiibosResponseCallback) {
        AmiiboRepository.downloadAmiibos(callback)
    }
}