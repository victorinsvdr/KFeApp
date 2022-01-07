package com.example.kfeapp.db.drink

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "drink")
data class Drink(
    @PrimaryKey(autoGenerate = true)
    var drinkId: Long = 0L,

    @ColumnInfo(name = "name")
    var drinkName: String,

    @ColumnInfo(name = "description")
    var drinkDescription: String,

    @ColumnInfo(name = "price")
    var drinkPrice: Double
)