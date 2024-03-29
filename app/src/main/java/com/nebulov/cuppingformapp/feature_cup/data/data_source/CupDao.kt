package com.nebulov.cuppingformapp.feature_cup.data.data_source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nebulov.cuppingformapp.feature_cup.domain.model.Cup
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

    @Query("DELETE FROM cup WHERE timestamp = :timestamp")
    suspend fun deleteSession(timestamp: Long)
}