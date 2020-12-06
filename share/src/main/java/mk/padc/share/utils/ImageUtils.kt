package mk.padc.share.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class ImageUtils{


    fun showImage( imageView: ImageView, imageUrl: String , thumbnail : Int)
    {
        Glide.with(imageView.context)
                .load( imageUrl)
                .placeholder(thumbnail)
               .apply(RequestOptions().circleCrop())
                .into(imageView)
    }
}
