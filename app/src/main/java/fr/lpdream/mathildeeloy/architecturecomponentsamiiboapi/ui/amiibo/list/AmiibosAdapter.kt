package fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.ui.amiibo.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.R
import fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.data.Amiibo
import kotlinx.android.synthetic.main.item_amiibo.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.sdk27.coroutines.onLongClick

class AmiibosAdapter: RecyclerView.Adapter<AmiibosAdapter.AmiiboViewHolder>() {

    var onClick: ((item: Amiibo) -> Unit)? = null
    var onLongClick: ((item: Amiibo) -> Unit)? = null

    private var data = listOf<Amiibo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AmiiboViewHolder =
        AmiiboViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_amiibo,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: AmiiboViewHolder, position: Int) {
        holder.bind(data[position], object:
        OnAmiiboClickListener {
            override fun onItemClick(amiibo: Amiibo) {
                onClick?.invoke(amiibo)
            }

            override fun onItemLongClick(amiibo: Amiibo) {
                onLongClick?.invoke(amiibo)
            }
        })
    }

    fun replaceData(newData: List<Amiibo>) {
        this.data = newData
        notifyDataSetChanged()
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