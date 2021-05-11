package com.example.package_delivery_app_group_a.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.package_delivery_app_group_a.R
import com.example.package_delivery_app_group_a.ui.manager.account.AccountFragment
import java.io.IOException

class GlideLoaderTwo(val context: AccountFragment) {
    fun loadUserPicture(image: Any, imageView: ImageView) {
        try {
            // Load the user image in the ImageView.
            Glide
                .with(context)
//                .load(Uri.parse(imageURI.toString())) // URI of the image
                .load(image)
                .centerCrop() // Scale type of the image.
                .placeholder(R.drawable.ic_user_placeholder) // A default place holder if image is failed to load.
                .into(imageView) // the view in which the image will be loaded.
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}