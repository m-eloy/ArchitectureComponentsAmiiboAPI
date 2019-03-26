package fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.ui.amiibo.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.data.model.Amiibo
import fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.data.AmiiboRepository
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class DetailAmiiboViewModel(application: Application): AndroidViewModel(application), KoinComponent {

    private val amiiboRepository: AmiiboRepository by inject()

    var amiiboId: MutableLiveData<Int> = MutableLiveData()

    var amiibo: LiveData<Amiibo> = Transformations.switchMap(amiiboId) { id -> amiiboRepository.getById(id) }
}