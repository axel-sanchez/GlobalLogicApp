package com.example.globallogicapp.data.model

import android.view.View
import com.example.globallogicapp.helpers.Constants.Error
import com.example.globallogicapp.helpers.Constants.Error.*
import com.example.globallogicapp.helpers.Constants.NO_PRODUCTS


/**
 * @author Axel Sanchez
 */
data class ResultListProducts(var listProducts: List<Product?>?, val error: Error) {

    val visibilityProgress = View.GONE

    var needToShowEmptyState = error !is NoError || listProducts?.isEmpty() ?: true

    var nameError = when (error) {
        NoError ->{
            listProducts?.let { 
                if (it.isEmpty()) NO_PRODUCTS else ""
            }?: kotlin.run { "" }
        }
        is UnknownError -> error.nameError
        is ApiError -> error.nameError
        is AppError -> error.nameError
    }
}