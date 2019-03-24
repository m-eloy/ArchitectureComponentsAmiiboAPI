package fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.data

import android.app.Application
import android.util.Log

object AmiiboRepository {

    private lateinit var database: AmiiboDatabase

    private lateinit var amiiboDao: AmiiboDao

    fun initialize(application: Application) {
        database = AmiiboDatabase.buildInstance(application)
        amiiboDao = database.amiiboDao()
    }

    fun insertAll(amiibos: List<Amiibo>) {
        amiibos.forEach { amiibo -> if(amiibo.id == 0) amiibo.id = (getAll().maxBy { it.id }?.id ?: 0) + 1}
        amiiboDao.insertAll(amiibos)
        Log.d("amiiboRepository", "inserting amiibo: $amiibos")
    }

    fun insert(amiibo: Amiibo) = insertAll(listOf(amiibo))

    fun delete(amiibo: Amiibo) {
        amiiboDao.delete(amiibo)
    }

    fun getById(id: Int): Amiibo = amiiboDao.getById(id)

    fun getAll(): List<Amiibo> = amiiboDao.getAll()
}