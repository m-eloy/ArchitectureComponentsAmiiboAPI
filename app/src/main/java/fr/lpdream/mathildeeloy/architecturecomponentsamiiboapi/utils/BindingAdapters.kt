package fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.R

@BindingAdapter("cover")
fun cover(view: ImageView, imageUrl: String?) {
    Picasso.get()
        .load(imageUrl)
        .error(R.drawable.ic_error_black_24dp)
        .into(view)
}