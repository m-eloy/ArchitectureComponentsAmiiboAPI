package fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Amiibo::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class AmiiboDatabase : RoomDatabase() {

    abstract fun amiiboDao(): AmiiboDao

    companion object {

        fun buildInstance(context: Context) = Room
            .databaseBuilder(context, AmiiboDatabase::class.java, "AmiiboDatabase")
            .build()
    }
}