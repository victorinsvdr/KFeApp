package com.example.kfeapp.db.drink

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DrinkDao {
    @Insert
    fun insertDrink(drink: Drink)

    @Query ("SELECT * FROM drink ORDER BY name ASC")
    fun getAllDrinks() : List<Drink>
}