package fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.extension

import java.text.SimpleDateFormat
import java.util.*

fun Date.dateToString(): String {
    val formatter = SimpleDateFormat("EEEE d MMMM yyyy", Locale.getDefault())
    return "Amiibo créé le ${formatter.format(this)}"
}