package com.nebulov.hluppr.feature_cup.domain.repository

import com.nebulov.hluppr.feature_cup.domain.model.Cup
import kotlinx.coroutines.flow.Flow


interface CupRepository {

    fun getCups(): Flow<List<Cup>>

    suspend fun getCupById(cupId: Int): Cup?

    suspend fun insertCup(cup: Cup)

    suspend fun deleteCup(cup: Cup)
    suspend fun deleteSession(timestamp: Long)
}