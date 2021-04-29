package com.example.jpwingsnikkeibpomikuji

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity

//not{ but(
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    var name: String,

    @ColumnInfo(name = "nenrei")
    var age: Int
)