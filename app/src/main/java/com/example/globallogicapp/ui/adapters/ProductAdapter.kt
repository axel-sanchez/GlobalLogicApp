package com.example.globallogicapp.ui.adapters

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.globallogicapp.R
import com.example.globallogicapp.data.model.Product

/**
 * @author Axel Sanchez
 */
class ProductAdapter(
    private val values: List<Product?>,
    private val itemClick: (Product?) -> Unit
) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]

        item?.let { product ->

            with(holder){
                title.text = product.title

                Glide
                    .with(itemView)
                    .load(product.image)
                    .centerCrop()
                    .into(image)

                description.text = product.description

                itemView.setOnClickListener { itemClick(product) }
            }
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.image)
        val title: TextView = view.findViewById(R.id.title)
        val description: TextView = view.findViewById(R.id.littleDescription)
    }
}