package com.example.kfeapp.db.dessert

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dessert")
data class Dessert(
    @PrimaryKey(autoGenerate = true)
    var dessertId: Long = 0L,

    @ColumnInfo(name = "name")
    var dessertName: String,

    @ColumnInfo(name = "description")
    var dessertDescription: String,

    @ColumnInfo(name = "price")
    var dessertPrice: Double
)