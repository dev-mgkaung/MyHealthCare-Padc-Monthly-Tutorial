package mk.padc.share.utils

import android.content.Context
import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import mk.padc.share.R

class ImageUtils{


    fun showImage( imageView: ImageView, imageUrl: String , thumbnail : Int)
    {
        Glide.with(imageView.context)
                .load( imageUrl)
                .placeholder(thumbnail)
                .apply(RequestOptions().circleCrop())
                .into(imageView)
    }

    fun showImageWithoutCrop( imageView: ImageView, imageUrl: String , thumbnail : Int)
    {
        Glide.with(imageView.context)
            .load( imageUrl)
            .placeholder(thumbnail)
            .into(imageView)
    }

    fun showImageProfile(context: Context, imageView: ImageView, imageUrl: String?, filePath: Uri?)
    {
        Glide.with(context)
            .asBitmap()
            .load(filePath ?: imageUrl)
            .placeholder(R.drawable.user)
            .apply(RequestOptions().circleCrop())
            .into(imageView)
    }

}
