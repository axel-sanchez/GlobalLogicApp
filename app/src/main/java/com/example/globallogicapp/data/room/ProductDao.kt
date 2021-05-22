package com.example.globallogicapp.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.globallogicapp.data.model.Product

/**
 * @author Axel Sanchez
 */
@Dao
interface ProductDao {
    @Query("SELECT * FROM Product")
    suspend fun getAllProducts(): List<Product?>

    @Query("SELECT * FROM Product where id = :idProduct")
    suspend fun getProduct(idProduct: Long): Product?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(product: Product?): Long
}