package com.example.kfeapp.drink

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.kfeapp.db.drink.Drink
import com.example.kfeapp.db.drink.DrinkDao
import com.example.kfeapp.db.user.User

import com.example.kfeapp.db.user.UserDao
import kotlinx.coroutines.launch
import java.math.BigInteger
import java.security.MessageDigest

class DrinkViewModel(
    val database: DrinkDao,
    application: Application
) : AndroidViewModel(application) {


    override fun onCleared() {
        super.onCleared()
        Log.i("DrinkViewModel", "DrinkViewModel destroyed!")
    }

    fun getAllDrinks(): List<Drink> {
        return database.getAllDrinks()
    }

    fun insert(drink: Drink) {
        viewModelScope.launch {
            database.insertDrink(drink)
        }
    }
}