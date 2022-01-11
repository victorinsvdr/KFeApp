package com.example.kfeapp.order

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.kfeapp.db.drink.Drink
import com.example.kfeapp.db.drink.DrinkDao
import com.example.kfeapp.db.food.Food
import com.example.kfeapp.db.food.FoodDao
import com.example.kfeapp.db.order.Order
import com.example.kfeapp.db.order.OrderDao
import com.example.kfeapp.db.user.User

import com.example.kfeapp.db.user.UserDao
import kotlinx.coroutines.launch
import java.math.BigInteger
import java.security.MessageDigest

class OrderViewModel(
    val database: OrderDao,
    application: Application
) : AndroidViewModel(application) {


    override fun onCleared() {
        super.onCleared()
        Log.i("OrderViewModel", "OrderViewModel destroyed!")
    }

    fun getAllOrders(): List<Order> {
        return database.getAllOrders()
    }

    fun insert(order: Order) {
        viewModelScope.launch {
            database.insertOrder(order)
        }
    }
}