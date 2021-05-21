package com.example.globallogicapp.helpers

object Constants {
    const val BASE_URL = "http://private-f0eea-mobilegllatam.apiary-mock.com/"

    enum class ApiError(var error: String) {
        API_ERROR("Error al obtener los productos")
    }
}