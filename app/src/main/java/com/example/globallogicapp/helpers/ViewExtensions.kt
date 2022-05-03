package com.example.globallogicapp.helpers

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

/**
 * @author Axel Sanchez
 */
fun View.show(){
    this.visibility = View.VISIBLE
}

fun View.hide(){
    this.visibility = View.GONE
}

@BindingAdapter("loadImage")
fun ImageView.loadImage(imageUrl: String?) {
    Glide
        .with(this.context)
        .load(imageUrl)
        .centerCrop()
        .into(this)
}