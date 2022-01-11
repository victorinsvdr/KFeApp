package com.example.kfeapp.history

data class HistoryModel(
    val food_n: String,
    val food_p: Double,
    val drink_n: String,
    val drink_p: Double,
    val dessert_n: String,
    val dessert_p: Double,
    val total: Double,
    val orderId: Long
)