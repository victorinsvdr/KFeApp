package com.example.kfeapp.dessert

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kfeapp.db.dessert.DessertDao
import com.example.kfeapp.db.drink.DrinkDao
import com.example.kfeapp.db.user.UserDao

class DessertViewModelFactory (
    private val dataSource: DessertDao,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DessertViewModel::class.java)) {
            return DessertViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
