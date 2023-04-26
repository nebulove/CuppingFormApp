package com.nebulov.cuppingformapp.feautere_cup.domain.use_case

import com.nebulov.cuppingformapp.feautere_cup.domain.model.Cup
import com.nebulov.cuppingformapp.feautere_cup.domain.repository.CupRepository

class GetCup(
    private val repository: CupRepository
) {

    suspend operator fun invoke(id: Int): Cup?{
        return repository.getCupById(id)
    }
}