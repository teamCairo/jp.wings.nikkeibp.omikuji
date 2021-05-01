package com.example.jpwingsnikkeibpomikuji

import androidx.room.*

@Dao
interface M_H_MondaiDao {


    @Query("SELECT * FROM m_h_mondai WHERE id = :taishoid")
    fun getTaishoMHMondai(taishoid: Int): M_H_Mondai

    @Query("SELECT * FROM m_h_mondai WHERE mondai_naiyo LIKE :naiyo")//LIKE '％'||   ||'％'
    fun getbyNaiyo(naiyo: String): List<M_H_Mondai>

    @Query("SELECT * FROM m_h_mondai WHERE rowId = :rowId")
    fun getRowIdMHMondai(rowId: Long): M_H_Mondai

    @Query("SELECT * FROM m_h_mondai order by id")
    fun getAll(): List<M_H_Mondai>

    @Query("DELETE FROM m_h_mondai")
    fun deleteAll()

    @Insert
    fun insert(mhmondai:M_H_Mondai): Long

    @Update
    fun update(mhmondai:M_H_Mondai)

    @Delete
    fun delete(mhmondai:M_H_Mondai)
}

