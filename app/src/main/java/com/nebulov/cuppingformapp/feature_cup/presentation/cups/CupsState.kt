package com.nebulov.cuppingformapp.feature_cup.presentation.cups

import com.nebulov.cuppingformapp.feature_cup.domain.model.Cup
import com.nebulov.cuppingformapp.feature_cup.domain.util.CupOrder
import com.nebulov.cuppingformapp.feature_cup.domain.util.OrderType

data class CupsState(
    val cups: List<Cup> = emptyList(),
    val cupOrder: CupOrder = CupOrder.Date(OrderType.Descending)

)
