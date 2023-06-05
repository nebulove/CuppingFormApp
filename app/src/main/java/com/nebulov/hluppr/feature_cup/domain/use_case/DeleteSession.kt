package com.nebulov.hluppr.feature_cup.domain.use_case

import com.nebulov.hluppr.feature_cup.domain.repository.CupRepository

class DeleteSession(
    private val repository: CupRepository
) {

    suspend operator fun invoke(timestamp: Long) {
        repository.deleteSession(timestamp)
    }
}