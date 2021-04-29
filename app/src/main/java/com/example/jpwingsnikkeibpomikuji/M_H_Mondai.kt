package com.example.jpwingsnikkeibpomikuji

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class M_H_Mondai (
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var mondai_naiyo: String
    )