package com.example.globallogicapp.helpers

object Constants {
    const val BASE_URL = "http://private-f0eea-mobilegllatam.apiary-mock.com/"

    const val NO_PRODUCTS = "No se obtuvo ningún producto"

    sealed class Error{
        data class ApiError(val nameError: String): Error()
        data class AppError(val nameError: String): Error()
        data class UnknownError(val nameError: String = "UnknownError"): Error()
        object NoError: Error()
    }
}