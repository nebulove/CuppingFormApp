package com.nebulov.cuppingformapp.feautere_cup.presentation.cups

import com.nebulov.cuppingformapp.feautere_cup.domain.model.Cup
import com.nebulov.cuppingformapp.feautere_cup.domain.util.CupOrder

sealed class CupEvent{

    data class Order(val cupOrder: CupOrder) : CupEvent()
    data class DeleteCup( val cup: Cup): CupEvent()
    object RestoreCup: CupEvent()
}
