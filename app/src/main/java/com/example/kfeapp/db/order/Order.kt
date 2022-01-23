package com.example.kfeapp.db.order

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "order")
data class Order(
    @PrimaryKey(autoGenerate = true)
    var orderId: Long = 0L,

    @ColumnInfo(name = "user")
    var user: String,

    @ColumnInfo(name = "food_n")
    var foodName: String,

    @ColumnInfo(name = "food_p")
    var foodPrice: Double,

    @ColumnInfo(name = "drink_n")
    var drinkName: String,

    @ColumnInfo(name = "drink_p")
    var drinkPrice: Double,

    @ColumnInfo(name = "dessert_n")
    var dessertName: String,

    @ColumnInfo(name = "dessert_p")
    var dessertPrice: Double,

    @ColumnInfo(name = "total")
    var total: Double,
)