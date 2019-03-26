package fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.ui.base

import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.BR
import fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.data.model.BaseObject
import fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.utils.OnItemClickListener

abstract class BaseViewHolder<T: BaseObject, V: ViewDataBinding>(private val viewDataBinding: V): RecyclerView.ViewHolder(viewDataBinding.root) {

    protected lateinit var item: T

    open fun bind(lifecycleOwner: LifecycleOwner, item: T, listener: OnItemClickListener<T>) {
        this.item = item
        viewDataBinding.setLifecycleOwner(lifecycleOwner)
        viewDataBinding.setVariable(BR.item, item)
        viewDataBinding.setVariable(BR.listener, listener)
        viewDataBinding.executePendingBindings()
    }
}