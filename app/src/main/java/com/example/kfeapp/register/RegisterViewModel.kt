package com.example.kfeapp.register

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.kfeapp.db.user.User

import com.example.kfeapp.db.user.UserDao
import kotlinx.coroutines.launch
import java.math.BigInteger
import java.security.MessageDigest

class RegisterViewModel(
    val database: UserDao,
    application: Application
) : AndroidViewModel(application) {


    override fun onCleared() {
        super.onCleared()
        Log.i("RegisterViewModel", "RegisterViewModel destroyed!")
    }

    fun insert(usr: User) {
        viewModelScope.launch {
            database.insertUser(usr)
        }
    }
}