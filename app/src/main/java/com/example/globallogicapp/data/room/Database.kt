package com.example.globallogicapp.data.room

import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.Database
import com.example.globallogicapp.data.model.Product

/**
 * Base de datos utilizando room
 * @author Axel Sanchez
 */
@Database(
    entities = [Product::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class Database: RoomDatabase() {
    abstract fun productDao(): ProductDao
}