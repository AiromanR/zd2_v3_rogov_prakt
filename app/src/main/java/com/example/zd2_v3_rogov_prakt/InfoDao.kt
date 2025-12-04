package com.example.zd2_v3_rogov_prakt

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface InfoDao {
    @Query("SELECT * FROM infos")
    fun getAll(): List<Info>

    @Insert
    fun insert(info: Info)

    @Delete
    fun delete(info: Info)
}