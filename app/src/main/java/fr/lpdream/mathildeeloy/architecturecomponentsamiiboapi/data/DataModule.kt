package fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.data

import androidx.room.Room
import fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.data.locale.AmiiboDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.module

val dataModule = module {

    single { Room.databaseBuilder(androidApplication(), AmiiboDatabase::class.java, AmiiboDatabase.DATABASE_NAME).build() }

    single { get<AmiiboDatabase>().amiiboDao() }

    single { AmiiboRepository() }
}