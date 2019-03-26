package fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.utils

import fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.data.locale.BaseObject

interface OnItemClickListener<T: BaseObject> {

    fun onItemClick(item: T)

    fun onItemLongClick(item: T): Boolean
}