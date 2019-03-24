package fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.ui.amiibo.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.R
import fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.data.Amiibo
import kotlinx.android.synthetic.main.item_amiibo.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.sdk27.coroutines.onLongClick

class AmiibosAdapter: ListAdapter<Amiibo, AmiibosAdapter.AmiiboViewHolder>(AmiiboDiffCallback()) {

    var onClick: ((item: Amiibo) -> Unit)? = null
    var onLongClick: ((item: Amiibo) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AmiiboViewHolder =
        AmiiboViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_amiibo, parent, false)
        )

    override fun onBindViewHolder(holder: AmiiboViewHolder, position: Int) {
        holder.bind(getItem(position), object: OnAmiiboClickListener {
            override fun onItemClick(amiibo: Amiibo) {
                onClick?.invoke(amiibo)
            }

            override fun onItemLongClick(amiibo: Amiibo) {
                onLongClick?.invoke(amiibo)
            }
        })
    }

    class AmiiboDiffCallback: DiffUtil.ItemCallback<Amiibo>() {

        override fun areContentsTheSame(oldItem: Amiibo, newItem: Amiibo): Boolean = oldItem == newItem

        override fun areItemsTheSame(oldItem: Amiibo, newItem: Amiibo): Boolean = oldItem.id == newItem.id
    }

    class AmiiboViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bind(amiibo: Amiibo, onAmiiboClickListener: OnAmiiboClickListener) {
            itemView.character.text = amiibo.character
            itemView.gameSeries.text = amiibo.gameSeries
            itemView.root.apply {
                onClick { onAmiiboClickListener.onItemClick(amiibo) }
                onLongClick { onAmiiboClickListener.onItemLongClick(amiibo) }
            }
        }
    }

    interface OnAmiiboClickListener {

        fun onItemClick(amiibo: Amiibo)

        fun onItemLongClick(amiibo: Amiibo)

    }
}