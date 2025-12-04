package com.example.zd2_v3_rogov_prakt

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context

@Database(entities = [Info::class], version = 2)
abstract class InfoDatabase : RoomDatabase() {
    abstract fun infoDao(): InfoDao

    companion object {
        private var database: InfoDatabase? = null

        fun getDatabase(context: Context): InfoDatabase {
            if (database == null) {
                database = Room.databaseBuilder(
                    context,
                    InfoDatabase::class.java,
                    "info_database"
                ).allowMainThreadQueries()
                    .build()
            }
            return database!!
        }
    }
}