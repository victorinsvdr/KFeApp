package com.example.kfeapp.order

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kfeapp.db.drink.DrinkDao
import com.example.kfeapp.db.food.FoodDao
import com.example.kfeapp.db.order.OrderDao

class OrderViewModelFactory (
    private val dataSource: OrderDao,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(OrderViewModel::class.java)) {
            return OrderViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
