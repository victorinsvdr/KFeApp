package com.example.kfeapp.food

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
import com.example.kfeapp.db.user.User

import com.example.kfeapp.db.user.UserDao
import kotlinx.coroutines.launch
import java.math.BigInteger
import java.security.MessageDigest

class FoodViewModel(
    val database: FoodDao,
    application: Application
) : AndroidViewModel(application) {


    override fun onCleared() {
        super.onCleared()
        Log.i("FoodViewModel", "FoodViewModel destroyed!")
    }

    fun getAllFoods(): List<Food> {
        return database.getAllFoods()
    }

    fun insert(food: Food) {
        viewModelScope.launch {
            database.insertFood(food)
        }
    }
}