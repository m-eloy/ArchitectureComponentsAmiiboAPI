package fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.ui.amiibo.list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.data.Amiibo
import fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.data.AmiiboRepository

class AmiibosViewModel(application: Application) : AndroidViewModel(application) {

    var amiibos: LiveData<List<Amiibo>> = AmiiboRepository.getAll()

    fun delete(amiibo: Amiibo) {
        AmiiboRepository.delete(amiibo)
    }
}