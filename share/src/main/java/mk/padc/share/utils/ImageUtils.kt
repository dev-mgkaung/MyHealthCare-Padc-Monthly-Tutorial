package mk.padc.share.utils

import android.content.Context
import android.net.Uri
import android.widget.ImageView
import androidx.fragment.app.FragmentActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import mk.padc.share.R

class ImageUtils{

    fun showImage(context: Context, imageView: ImageView, imageUrl: String?, filePath: Uri?)
    {
        Glide.with(context)
            .asBitmap()
            .load(filePath ?: imageUrl)
            .placeholder(R.drawable.profile_thumb)
            .apply(RequestOptions().circleCrop())
            .into(imageView)
    }

    fun showImageWithoutCrop( imageView: ImageView, imageUrl: String)
    {
        Glide.with(imageView.context)
                .load( imageUrl)
                .placeholder(R.drawable.thumbnail)
                .into(imageView)
    }
}
