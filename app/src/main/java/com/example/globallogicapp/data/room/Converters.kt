package com.example.globallogicapp.data.room

import androidx.room.TypeConverter
import com.example.globallogicapp.data.model.Product
import com.google.gson.Gson

/**
 * @author Axel Sanchez
 */
class Converters{
    private val gson: Gson = Gson()

    @TypeConverter
    fun fromProduct(product: Product?): String? {
        product?.let {
            return gson.toJson(it)
        } ?: return null
    }

    @TypeConverter
    fun toProduct(resultItemString: String?): Product? {
        resultItemString?.let {
            return gson.fromJson(it, Product::class.java)
        } ?: return null
    }

    @TypeConverter
    fun fromListProducts(list: List<Product?>?): String? {
        var response = ""
        list?.let {
            for (i in list.indices) {
                response += if (i == 0) fromProduct(it[i])
                else ";${fromProduct(it[i])}"
            }
        } ?: return null
        return response
    }

    @TypeConverter
    fun toListProducts(concat: String?): List<Product?>? {
        val newList = concat?.split(";")
        newList?.let {
            return it.map { str -> toProduct(str) }
        } ?: return null
    }
}