package com.nebulov.cuppingformapp.feature_cup.domain.use_case

data class CupUseCases(
    val getCupList: GetCupList,
    val deleteCup: DeleteCup,
    val addCup: AddCup,
    val getCup: GetCup,
    val deleteSession: DeleteSession

)
