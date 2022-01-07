package com.example.kfeapp.drink

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kfeapp.db.drink.DrinkDao
import com.example.kfeapp.db.user.UserDao

class DrinkViewModelFactory (
    private val dataSource: DrinkDao,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DrinkViewModel::class.java)) {
            return DrinkViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
