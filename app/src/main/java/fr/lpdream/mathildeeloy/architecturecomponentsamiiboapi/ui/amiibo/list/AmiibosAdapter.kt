package fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.ui.amiibo.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.R
import fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.data.model.Amiibo
import fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.databinding.ItemAmiiboBinding
import fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.ui.base.BaseAdapter
import fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.ui.base.BaseViewHolder
import fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.utils.OnItemClickListener

class AmiibosAdapter(lifecycleOwner: LifecycleOwner): BaseAdapter<Amiibo>(lifecycleOwner) {

    override fun layoutFor(position: Int): Int = R.layout.item_amiibo

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Amiibo, *> {
        val binding: ItemAmiiboBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), viewType, parent, false)
        return AmiiboViewHolder(binding)
    }

    class AmiiboViewHolder(private val binding: ItemAmiiboBinding): BaseViewHolder<Amiibo, ItemAmiiboBinding>(binding) {

        override fun bind(lifecycleOwner: LifecycleOwner, item: Amiibo, listener: OnItemClickListener<Amiibo>) {
            super.bind(lifecycleOwner, item, listener)
            binding.character.text = item.character
        }
    }
}