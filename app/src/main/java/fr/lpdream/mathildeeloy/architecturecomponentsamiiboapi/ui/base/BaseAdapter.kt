package fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.ui.base

import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.ListAdapter
import fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.data.locale.BaseObject
import fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.utils.DiffCallback
import fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.utils.OnItemClickListener

abstract class BaseAdapter<T: BaseObject>(private val lifecycleOwner: LifecycleOwner, updateWhen: ((T, T) -> Boolean)? = null):
        ListAdapter<T, BaseViewHolder<T, *>>(DiffCallback<T>(updateWhen)) {

    var onClick: ((item: T) -> Unit)? = null
    var onLongClick: ((item: T) -> Unit)? = null

    override fun onBindViewHolder(holder: BaseViewHolder<T, *>, position: Int) {
        holder.bind(lifecycleOwner, getItem(position), object: OnItemClickListener<T> {
            override fun onItemClick(item: T) {
                onClick?.invoke(item)
            }

            override fun onItemLongClick(item: T): Boolean {
                onLongClick?.invoke(item)
                return true
            }
        })
    }

    override fun getItemViewType(position: Int): Int = layoutFor(position)

    protected abstract fun layoutFor(position: Int): Int
}