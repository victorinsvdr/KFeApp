package com.example.kfeapp.db.user

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = true)
    var userId: Long = 0L,

    @ColumnInfo(name = "username")
    var userName: String,

    @ColumnInfo(name = "password")
    var password: String
)