package com.example.kfeapp.db.user

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {
    @Insert
    fun insertUser(user: User)

    @Query ("SELECT * FROM user WHERE userId = :key")
    fun getUserById(key: Long): User?

    @Query ("SELECT username FROM user")
    fun getAllNames(): List<String>

    @Query ("SELECT username FROM user WHERE userId = :key")
    fun getUserName(key: Long): String

    @Query ("SELECT password FROM user WHERE userId = :key")
    fun getUserPassword(key: Long): String

    @Query ("DELETE from user WHERE userId = :key")
    fun deleteUserById(key: Long)
}