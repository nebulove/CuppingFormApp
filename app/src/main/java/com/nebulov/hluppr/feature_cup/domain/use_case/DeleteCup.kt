package com.nebulov.hluppr.feature_cup.domain.use_case

import com.nebulov.hluppr.feature_cup.domain.model.Cup
import com.nebulov.hluppr.feature_cup.domain.repository.CupRepository

class DeleteCup(
    private val repository: CupRepository
) {

    suspend operator fun invoke(cup: Cup) {
        repository.deleteCup(cup)
    }
}