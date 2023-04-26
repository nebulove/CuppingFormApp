package com.nebulov.cuppingformapp.feautere_cup.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nebulov.cuppingformapp.feautere_cup.domain.model.Cup

@Database
    (
    entities = [Cup::class],
    version = 1,
    exportSchema = false
)


abstract class CupDatabase: RoomDatabase() {

    abstract val cupDao: CupDao
}