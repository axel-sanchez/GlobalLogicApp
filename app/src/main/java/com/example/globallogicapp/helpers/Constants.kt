package com.example.globallogicapp.helpers

object Constants {
    const val BASE_URL = "http://private-f0eea-mobilegllatam.apiary-mock.com/"

    const val NO_PRODUCTS = "No se obtuvo ningún producto"
    const val API_ERROR = "Error al obtener los productos"

    sealed class Error{
        object ApiError: Error()
        object NoError: Error()
    }
}