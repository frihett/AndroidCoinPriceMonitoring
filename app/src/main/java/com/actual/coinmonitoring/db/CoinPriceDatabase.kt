package com.actual.coinmonitoring.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.actual.coinmonitoring.db.dao.InterestCoinDAO
import com.actual.coinmonitoring.db.dao.SelectedCoinPriceDAO
import com.actual.coinmonitoring.db.entity.DateConverters
import com.actual.coinmonitoring.db.entity.InterestCoinEntity
import com.actual.coinmonitoring.db.entity.SelectedCoinPriceEntity

@Database(entities = [InterestCoinEntity::class, SelectedCoinPriceEntity::class], version = 2)
@TypeConverters(DateConverters::class)
abstract class CoinPriceDatabase : RoomDatabase(){

    abstract fun interestCoinDAO() : InterestCoinDAO
    abstract fun selectedCoinDAO() : SelectedCoinPriceDAO

    companion object {

        @Volatile
        private var INSTANCE : CoinPriceDatabase? = null

        fun getDatabase(
            context : Context
        ) : CoinPriceDatabase {

            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CoinPriceDatabase::class.java,
                    "coin_database"
                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }

        }


    }


}