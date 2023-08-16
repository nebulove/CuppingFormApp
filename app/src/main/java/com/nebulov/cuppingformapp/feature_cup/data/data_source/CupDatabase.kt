package com.nebulov.cuppingformapp.feature_cup.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.nebulov.cuppingformapp.feature_cup.domain.model.Cup

@Database
    (
    entities = [Cup::class],
    version = 3,
    exportSchema = false
)


abstract class CupDatabase: RoomDatabase() {

    abstract val cupDao: CupDao

    companion object{

        val MIGRATION_2_3 = object : Migration(2,3){
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE Cup ADD COLUMN setId INTEGER DEFAULT 0 NOT NULL")
                database.execSQL("ALTER TABLE Cup ADD COLUMN setName TEXT DEFAULT '' NOT NULL")
            }
        }

        const val DATABASE_NAME = "cups_db"
    }
}