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

    private var _drink: MutableLiveData<String> = MutableLiveData()
    val drink: LiveData<String> = _drink

    private var _drinkPrice: MutableLiveData<String> = MutableLiveData()
    val drinkPrice: LiveData<String> = _drinkPrice

    private var _food: MutableLiveData<String> = MutableLiveData()
    val food: LiveData<String> = _food

    private var _foodPrice: MutableLiveData<String> = MutableLiveData()
    val foodPrice: LiveData<String> = _foodPrice

    private var _dessert: MutableLiveData<String> = MutableLiveData()
    val dessert: LiveData<String> = _dessert

    private var _dessertPrice: MutableLiveData<String> = MutableLiveData()
    val dessertPrice: LiveData<String> = _dessertPrice


    fun login () {
        _logState.value = true
    }

    fun saveId (newId: Long) {
        _userId.value = newId
    }

    fun saveName (newName: String) {
        _userName.value = newName
    }

    fun saveDrink (newDrink: String, newPrice: String) {
        _drink.value = newDrink
        _drinkPrice.value = newPrice
    }

    fun saveFood (newFood: String, newPrice: String) {
        _food.value = newFood
        _foodPrice.value = newPrice
    }

    fun saveDessert (newDessert: String, newPrice: String) {
        _dessert.value = newDessert
        _dessertPrice.value = newPrice
    }

}