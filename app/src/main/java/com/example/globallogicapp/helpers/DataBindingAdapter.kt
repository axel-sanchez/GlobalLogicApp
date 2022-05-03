package com.example.globallogicapp.helpers

import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.os.bundleOf
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.globallogicapp.R
import com.example.globallogicapp.data.model.Product
import com.example.globallogicapp.presentation.ProductsFragment
import com.example.globallogicapp.presentation.adapters.ProductAdapter

/**
 * @author Axel Sanchez
 */
@BindingAdapter("loadImage")
fun loadImage(image: ImageView, imageUrl: String?) {
    Glide
        .with(image.context)
        .load(imageUrl)
        .centerCrop()
        .into(image)
}

@BindingAdapter("setAdapterBinding")
fun setAdapterBinding(recyclerView: RecyclerView, response: List<Product?>?) {
    response?.let { listProducts ->
        if (listProducts.isNotEmpty()) {
            recyclerView.show()
            with(recyclerView) {
                layoutManager = LinearLayoutManager(context)
                adapter = ProductAdapter(listProducts) { itemClick(it, recyclerView) }
            }
        } else recyclerView.hide()
    }
}

private fun itemClick(product: Product?, recyclerView: RecyclerView) {
    val bundle = bundleOf(
        ProductsFragment.ID_PRODUCT to product?.id
    )
    recyclerView.findNavController().navigate(R.id.action_productFragment_to_blankFragment, bundle)
}

@BindingAdapter("updateEmptyState")
fun updateEmptyState(emptyState: CardView, needToShowEmptyState: Boolean) {
    if (needToShowEmptyState) emptyState.show() else emptyState.hide()
}

@BindingAdapter("updateErrorText")
fun updateErrorText(textView: TextView, errorText: String?) {
    if (errorText?.isNotEmpty() == true){
        textView.text = errorText
    }
}