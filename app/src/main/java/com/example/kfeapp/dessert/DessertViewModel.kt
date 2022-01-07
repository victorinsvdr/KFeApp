package com.example.kfeapp.dessert

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.kfeapp.db.dessert.Dessert
import com.example.kfeapp.db.dessert.DessertDao
import com.example.kfeapp.db.drink.Drink
import com.example.kfeapp.db.drink.DrinkDao
import com.example.kfeapp.db.user.User

import com.example.kfeapp.db.user.UserDao
import kotlinx.coroutines.launch
import java.math.BigInteger
import java.security.MessageDigest

class DessertViewModel(
    val database: DessertDao,
    application: Application
) : AndroidViewModel(application) {


    override fun onCleared() {
        super.onCleared()
        Log.i("DessertViewModel", "DessertViewModel destroyed!")
    }

    fun getAllDesserts(): List<Dessert> {
        return database.getAllDesserts()
    }

    fun insert(dessert: Dessert) {
        viewModelScope.launch {
            database.insertDessert(dessert)
        }
    }
}