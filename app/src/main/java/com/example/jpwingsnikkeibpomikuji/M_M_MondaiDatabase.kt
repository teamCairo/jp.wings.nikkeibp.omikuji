package com.example.jpwingsnikkeibpomikuji

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [M_M_Mondai::class], version = 1)
abstract class M_M_MondaiDatabase : RoomDatabase() {

    abstract fun M_M_MondaiDao(): M_M_MondaiDao

    companion object{
        private var INSTANCE: M_M_MondaiDatabase? = null
        private val lock = Any()

        fun getInstance(context: Context): M_M_MondaiDatabase{
            synchronized(lock){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                            M_M_MondaiDatabase::class.java, "M_M_Mondai.db")
                            .allowMainThreadQueries()
                            .build()
                }
            }
            return INSTANCE!!
        }
    }
}