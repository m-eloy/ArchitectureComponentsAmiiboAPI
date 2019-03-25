package fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.ui.amiibo.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.data.locale.Amiibo
import fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.data.locale.AmiiboRepository

class DetailAmiiboViewModel(application: Application): AndroidViewModel(application) {

    var amiiboId: MutableLiveData<Int> = MutableLiveData()

    var amiibo: LiveData<Amiibo> = Transformations.switchMap(amiiboId) { id -> AmiiboRepository.getById(id) }
}