package com.example.globallogicapp.helpers

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide


/**
 * @author Axel Sanchez
 */
@BindingAdapter("loadImage")
fun loadImage(view: View, imageUrl: String?){
    val image: ImageView = view as ImageView
    Glide
        .with(view.context)
        .load(imageUrl)
        .centerCrop()
        .into(image)
}