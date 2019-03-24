package fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.ui.amiibo.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.R
import fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.data.Amiibo

class AmiibosAdapter: ListAdapter<Amiibo, AmiibosAdapter.AmiiboViewHolder>(AmiiboDiffCallback()) {

    var onClick: ((item: Amiibo) -> Unit)? = null
    var onLongClick: ((item: Amiibo) -> Unit)? = null

    override fun getItemViewType(position: Int): Int = R.layout.item_amiibo

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AmiiboViewHolder {
        val viewDataBinding: ViewDataBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), viewType, parent, false)
        return AmiiboViewHolder(viewDataBinding)
    }

    override fun onBindViewHolder(holder: AmiiboViewHolder, position: Int) {
        holder.bind(getItem(position), object: OnAmiiboClickListener {
            override fun onItemClick(amiibo: Amiibo) {
                onClick?.invoke(amiibo)
            }

            override fun onItemLongClick(amiibo: Amiibo): Boolean {
                onLongClick?.invoke(amiibo)
                return true
            }
        })
    }

    class AmiiboDiffCallback: DiffUtil.ItemCallback<Amiibo>() {

        override fun areContentsTheSame(oldItem: Amiibo, newItem: Amiibo): Boolean = oldItem == newItem

        override fun areItemsTheSame(oldItem: Amiibo, newItem: Amiibo): Boolean = oldItem.id == newItem.id
    }

    class AmiiboViewHolder(private val viewDataBinding: ViewDataBinding): RecyclerView.ViewHolder(viewDataBinding.root) {

        fun bind(amiibo: Amiibo, onAmiiboClickListener: OnAmiiboClickListener) {
            viewDataBinding.setVariable(BR.amiibo, amiibo)
            viewDataBinding.setVariable(BR.listener, onAmiiboClickListener)
            viewDataBinding.executePendingBindings()
        }
    }

    interface OnAmiiboClickListener {

        fun onItemClick(amiibo: Amiibo)

        fun onItemLongClick(amiibo: Amiibo): Boolean

    }
}