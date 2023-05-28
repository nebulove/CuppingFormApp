package com.nebulov.hluppr.feature_cup.presentation.cups

import com.nebulov.hluppr.feature_cup.domain.model.Cup
import com.nebulov.hluppr.feature_cup.domain.util.CupOrder

sealed class CupEvent{

    data class Order(val cupOrder: CupOrder) : CupEvent()
    data class DeleteCup( val cup: Cup ): CupEvent()
    data class ChangeFavorite( val cup: Cup): CupEvent()
    object RestoreCup: CupEvent()
}
