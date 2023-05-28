package com.nebulov.hluppr.feature_cup.domain.use_case

import com.nebulov.hluppr.feature_cup.domain.model.Cup
import com.nebulov.hluppr.feature_cup.domain.model.InvalidNoteException
import com.nebulov.hluppr.feature_cup.domain.repository.CupRepository
import kotlin.jvm.Throws

class AddCup(
    private val repository: CupRepository
) {

    @Throws(InvalidNoteException::class)
    suspend operator fun invoke( cup: Cup) {
        if (cup.name.isBlank()) {
            throw InvalidNoteException("The name of the cup can't be empty.")
        }
        repository.insertCup(cup)
    }
}