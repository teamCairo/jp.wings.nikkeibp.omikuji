package com.example.jpwingsnikkeibpomikuji

import androidx.room.Dao
import androidx.room.Query

@Dao
interface UserRepoDao {
    @Query("select * from MondaiDataforExcel")
    fun getAll(): List<MondaiDataforExcel>

    @Query("select * from MondaiDataforExcel where id = :id")
    fun getById(id:Int): MondaiDataforExcel
}