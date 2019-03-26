package fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "amiibo")
data class Amiibo(

    @PrimaryKey
    override var id: Int = 0,

    var amiiboSeries: String = "none",

    var character: String = "Sans nom",

    var gameSeries: String = "Sans jeu",

    var release: Date = Date(),

    var imageUrl: String? = null
): BaseObject