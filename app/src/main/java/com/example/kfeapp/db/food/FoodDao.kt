package com.example.kfeapp.db.food

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FoodDao {
    @Insert
    fun insertFood(food: Food)

    @Query ("SELECT * FROM food ORDER BY name ASC")
    fun getAllFoods() : List<Food>
}