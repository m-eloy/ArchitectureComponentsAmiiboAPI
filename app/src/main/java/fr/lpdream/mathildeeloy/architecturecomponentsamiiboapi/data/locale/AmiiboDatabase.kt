package fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.data.locale

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.data.model.Amiibo

@Database(entities = [Amiibo::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class AmiiboDatabase : RoomDatabase() {

    abstract fun amiiboDao(): AmiiboDao

    companion object {
        const val DATABASE_NAME = "AmiiboDatabase"
    }
}