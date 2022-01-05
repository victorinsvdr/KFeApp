package com.example.kfeapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    private var _userName: MutableLiveData<String> = MutableLiveData()
    val userName: LiveData<String> = _userName

    private var _userId: MutableLiveData<Long> = MutableLiveData()
    val userId: LiveData<Long> = _userId

    private var _logState: MutableLiveData<Boolean> = MutableLiveData()
    val logState: LiveData<Boolean> = _logState

    fun login () {
        _logState.value = true
    }

    fun saveId (newId: Long) {
        _userId.value = newId
    }

    fun saveName (newName: String) {
        _userName.value = newName
    }
}