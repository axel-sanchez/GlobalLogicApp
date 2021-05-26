package com.example.globallogicapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Product(
    @PrimaryKey(autoGenerate = true) var id: Long,
    var description: String? = null,
    var image: String? = null,
    var title: String? = null
)