package fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.utils

import fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.extension.dateToString
import java.util.*

object Utils {

    @JvmStatic fun dateToString(date: Date?): String = date?.dateToString() ?: ""

    @JvmStatic fun dateToStringCapitalize(date: Date?): String = dateToString(date).capitalize()
}