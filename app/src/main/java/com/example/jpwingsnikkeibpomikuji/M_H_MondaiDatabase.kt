package com.example.jpwingsnikkeibpomikuji

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [M_H_Mondai::class], version = 2)
abstract class M_H_MondaiDatabase : RoomDatabase() {

    abstract fun M_H_MondaiDao(): M_H_MondaiDao

    companion object{
        private var INSTANCE: M_H_MondaiDatabase? = null
        private val lock = Any()

        fun getInstance(context: Context): M_H_MondaiDatabase{
            synchronized(lock){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                            M_H_MondaiDatabase::class.java, "M_H_Mondai.db")
                            .allowMainThreadQueries()
                            .fallbackToDestructiveMigration()
                            .build()
                }
            }
            return INSTANCE!!
        }
    }
}