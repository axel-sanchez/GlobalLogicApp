package com.example.globallogicapp.domain.repository

import com.example.globallogicapp.data.model.Result
import com.example.globallogicapp.data.source.ApiError
import com.example.globallogicapp.helpers.Either

/**
 * @author Axel Sanchez
 */
interface ProductRepository {
    suspend fun getProducts(): Either<ApiError, Result>
}