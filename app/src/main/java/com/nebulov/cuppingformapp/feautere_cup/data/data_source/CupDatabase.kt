package com.nebulov.cuppingformapp.feautere_cup.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nebulov.cuppingformapp.feautere_cup.domain.model.Cup

@Database
    (
    entities = [Cup::class],
    version = 2,
    exportSchema = false
)


abstract class CupDatabase: RoomDatabase() {

    abstract val cupDao: CupDao

    companion object{

        const val DATABASE_NAME = "cups_db"
    }
}