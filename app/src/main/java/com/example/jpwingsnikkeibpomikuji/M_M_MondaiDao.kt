package com.example.jpwingsnikkeibpomikuji

import androidx.room.*

@Dao
interface M_M_MondaiDao {
    @Query("SELECT * FROM M_M_Mondai WHERE id = :taishoid")//
    fun getTaishoMMMondai(taishoid: Int): List<M_M_Mondai>


    @Query("SELECT * FROM M_M_Mondai WHERE id = :id and sentaku_id = :sentaku_id")//
    fun getByKey(id: Int,sentaku_id:Int): M_M_Mondai

    @Query("DELETE FROM m_m_mondai")
    fun deleteAll()

    @Insert
    fun insert(mmmondai:M_M_Mondai)

    @Update
    fun update(mmmondai:M_M_Mondai)

    @Delete
    fun delete(mmmondai:M_M_Mondai)
}

