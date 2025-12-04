package com.example.zd2_v3_rogov_prakt

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "infos")
data class Info(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val pulse: String = "",
    val pressure: String = "",
    val temperature: String = "",
    val date: String = ""
)