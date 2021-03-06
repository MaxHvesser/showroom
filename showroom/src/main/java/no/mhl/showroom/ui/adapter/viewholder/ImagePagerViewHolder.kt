package no.mhl.showroom.ui.adapter.viewholder

import android.view.View
import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.alexvasilkov.gestures.views.GestureImageView
import no.mhl.showroom.R
import no.mhl.showroom.model.GalleryImage
import no.mhl.showroom.util.dp

class ImagePagerViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    // region Properties
    private val imageView: GestureImageView by lazy { view.findViewById<GestureImageView>(R.id.row_image_gallery_image) }
    // endregion

    // region Binding
    fun bind(item: GalleryImage, imageClickedEvent: (() -> Unit)?) {
        imageView.load(item.url) {
            error(R.drawable.ic_error)
            crossfade(true)
            listener(
                onError = { _, _ ->
                    imageView.controller.settings.apply {
                        isZoomEnabled = false
                        isDoubleTapEnabled = false
                    }
                    imageView.updateLayoutParams {
                        height = 64.dp
                        width = 64.dp
                    }
                }
            )
        }
        imageView.setOnClickListener { imageClickedEvent?.invoke() }
    }
    // endregion

}