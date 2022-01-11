package com.example.kfeapp.db.order

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface OrderDao {
    @Insert
    fun insertOrder(order: Order)

    @Query ("SELECT * FROM `order`")
    fun getAllOrders() : List<Order>
}