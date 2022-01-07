package com.example.kfeapp.db.dessert

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DessertDao {
    @Insert
    fun insertDessert(dessert: Dessert)

    @Query ("SELECT * FROM dessert ORDER BY name ASC")
    fun getAllDesserts() : List<Dessert>
}