package com.example.jpwingsnikkeibpomikuji

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(primaryKeys = ["id" , "sentaku_id"])
data class M_M_Mondai(
        val id:Int,
        val sentaku_id:Int,
        var sentaku_naiyo:String,
        val seikai_Flg:Boolean
)