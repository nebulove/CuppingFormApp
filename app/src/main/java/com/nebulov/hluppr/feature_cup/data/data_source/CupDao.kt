package com.nebulov.hluppr.feature_cup.data.data_source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nebulov.hluppr.feature_cup.domain.model.Cup
import kotlinx.coroutines.flow.Flow

@Dao
interface CupDao {
    @Query("SELECT * FROM cup")
    fun getCupList(): Flow<List<Cup>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCup(cup: Cup)

    @Delete
    suspend fun deleteCup(cup: Cup)

    @Query("SELECT * FROM cup WHERE id = :id")
    suspend fun getCup(id: Int): Cup?
}