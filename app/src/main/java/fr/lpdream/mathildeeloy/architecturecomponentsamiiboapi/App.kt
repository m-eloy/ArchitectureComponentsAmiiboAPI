package fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi

import android.app.Application
import fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.data.Amiibo
import fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.data.AmiiboRepository
import org.jetbrains.anko.doAsync
import java.util.*

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        AmiiboRepository.initialize(this)

        val calendar = Calendar.getInstance()

        val amiibos = listOf(
            Amiibo(id = 0, amiiboSeries = "Super Smash Bros.", character = "Mario", gameSeries = "Super Mario", release = calendar.apply { set(2014, 11, 21) }.time),
            Amiibo(id = 1, amiiboSeries = "The Legend of Zelda", character = "Zelda", gameSeries = "The Legend of Zelda", release = calendar.apply { set(2014, 12, 12) }.time),
            Amiibo(id = 2, amiiboSeries = "Kirby", character = "Kirby", gameSeries = "Kirby", release = calendar.apply { set(2014, 11, 29) }.time)
        )

        doAsync { AmiiboRepository.insertAll(amiibos) }
    }
}
