package com.example.jpwingsnikkeibpomikuji

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [M_H_Mondai::class, M_M_Mondai::class], views = [MondaiDataforExcel::class], version = 1)
    abstract class AppDatabase : RoomDatabase() {
        abstract fun mhMondaiDao(): M_H_MondaiDao
        abstract fun mmMondaiDao(): M_M_MondaiDao
        abstract fun mondaiDataforExcelDao(): MondaiDataforExcelDao
    }