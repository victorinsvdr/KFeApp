package com.example.kfeapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.kfeapp.db.dessert.Dessert
import com.example.kfeapp.db.dessert.DessertDao
import com.example.kfeapp.db.drink.Drink
import com.example.kfeapp.db.drink.DrinkDao
import com.example.kfeapp.db.food.Food
import com.example.kfeapp.db.food.FoodDao
import com.example.kfeapp.db.user.User
import com.example.kfeapp.db.user.UserDao

@Database(entities = [User::class, Drink::class, Food::class, Dessert::class], version = 5, exportSchema = false)
abstract class KFeDB : RoomDatabase() {

    abstract val userDao: UserDao
    abstract val drinkDao: DrinkDao
    abstract val foodDao: FoodDao
    abstract val dessertDao: DessertDao

    companion object {

        @Volatile
        private var INSTANCE: KFeDB? = null

        fun getInstance(context: Context): KFeDB {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        KFeDB::class.java,
                        "kfedb"
                    )
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}
