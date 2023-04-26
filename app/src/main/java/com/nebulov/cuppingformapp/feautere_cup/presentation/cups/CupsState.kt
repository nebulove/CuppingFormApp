package com.nebulov.cuppingformapp.feautere_cup.presentation.cups

import com.nebulov.cuppingformapp.feautere_cup.domain.model.Cup
import com.nebulov.cuppingformapp.feautere_cup.domain.util.CupOrder
import com.nebulov.cuppingformapp.feautere_cup.domain.util.OrderType

data class CupsState(
    val cups: List<Cup> = emptyList(),
    val cupOrder: CupOrder = CupOrder.Date(OrderType.Descending)

)
