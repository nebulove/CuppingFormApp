package com.nebulov.cuppingformapp.feature_cup.domain.use_case

import com.nebulov.cuppingformapp.feature_cup.domain.model.Cup
import com.nebulov.cuppingformapp.feature_cup.domain.repository.CupRepository

class GetCup(
    private val repository: CupRepository
) {

    suspend operator fun invoke(cupId: Int): Cup?{
        return repository.getCupById(cupId)
    }
}