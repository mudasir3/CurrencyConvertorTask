package com.currencyconvertorassignment.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.currencyconvertorassignment.data.entities.Currency
import com.currencyconvertorassignment.data.entities.ExchangeRate
import com.currencyconvertorassignment.data.dao.CurrencyDao
import com.currencyconvertorassignment.data.dao.ExchangeRateDao

/**
 * declaration of app database, using room database library
 */
@Database(entities = [ExchangeRate::class, Currency::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun currencyDao(): CurrencyDao
    abstract fun exchangeRateDao(): ExchangeRateDao

    companion object {

        private const val DB_NAME = "currency-db"

        // For Singleton instantiation
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context,
                AppDatabase::class.java, DB_NAME
            ).build()
        }
    }

}