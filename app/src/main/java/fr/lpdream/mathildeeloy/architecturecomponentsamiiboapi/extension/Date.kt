package fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.extension

import java.text.SimpleDateFormat
import java.util.*

fun Date.dateToString(): String {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd")
    val strDate = dateFormat.format(this)
    if (strDate == "0000-00-00") {
        return "Cet amiibo n'est pas encore sorti"
    } else {
        val formatter = SimpleDateFormat("EEEE d MMMM yyyy", Locale.getDefault())
        return "Amiibo sorti le ${formatter.format(this)}"
    }
}