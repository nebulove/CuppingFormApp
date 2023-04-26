package com.nebulov.cuppingformapp.feautere_cup.data.repository

import com.nebulov.cuppingformapp.feautere_cup.data.data_source.CupDao
import com.nebulov.cuppingformapp.feautere_cup.domain.model.Cup
import com.nebulov.cuppingformapp.feautere_cup.domain.repository.CupRepository
import kotlinx.coroutines.flow.Flow

class CupRepositoryImpl(
    private val dao: CupDao
): CupRepository {

    override fun getCups(): Flow<List<Cup>> {
        return dao.getCupList()
    }

    override suspend fun getCupById(id: Int): Cup? {
        return dao.getCup(id)
    }

    override suspend fun insertCup(cup: Cup) {
        dao.insertCup(cup)
    }

    override suspend fun deleteCup(cup: Cup) {
        dao.deleteCup(cup)
    }
}