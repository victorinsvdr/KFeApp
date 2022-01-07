package com.example.kfeapp.db.food

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "food")
data class Food(
    @PrimaryKey(autoGenerate = true)
    var foodId: Long = 0L,

    @ColumnInfo(name = "name")
    var foodName: String,

    @ColumnInfo(name = "description")
    var foodDescription: String,

    @ColumnInfo(name = "price")
    var foodPrice: Double
)