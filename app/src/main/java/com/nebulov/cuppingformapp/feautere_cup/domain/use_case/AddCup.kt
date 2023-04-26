package com.nebulov.cuppingformapp.feautere_cup.domain.use_case

import com.nebulov.cuppingformapp.feautere_cup.domain.model.Cup
import com.nebulov.cuppingformapp.feautere_cup.domain.repository.CupRepository

class AddCup(
    private val repository: CupRepository
) {

    suspend operator fun invoke( cup: Cup) {
        if (cup.name.isBlank()) {
            val sampleCup = cup.copy(name = "Sample #${cup.id}")
            repository.insertCup(sampleCup)
        }
        repository.insertCup(cup)
    }
}