package com.nebulov.hluppr.feature_cup.domain.use_case

import com.nebulov.hluppr.feature_cup.domain.model.Cup
import com.nebulov.hluppr.feature_cup.domain.repository.CupRepository
import com.nebulov.hluppr.feature_cup.domain.util.CupOrder
import com.nebulov.hluppr.feature_cup.domain.util.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetCupList(
    private val repository: CupRepository
) {


    operator fun invoke(
        cupOrder: CupOrder = CupOrder.Date(OrderType.Descending)
    ): Flow<List<Cup>> {
        return repository.getCups().map { cups ->
            when (cupOrder.orderType) {
                is OrderType.Ascending -> {
                    when(cupOrder){
                        is CupOrder.Title -> cups.sortedBy { it.name.lowercase() }
                        is CupOrder.Date -> cups.sortedBy { it.timestamp }
                        is CupOrder.Value -> cups.sortedBy { it.finalScore }
                        is CupOrder.Favorite -> cups.sortedBy { it.favorite }

                    }
                }
                is OrderType.Descending -> {
                    when(cupOrder){
                        is CupOrder.Title -> cups.sortedByDescending { it.name.lowercase() }
                        is CupOrder.Date -> cups.sortedByDescending { it.timestamp }
                        is CupOrder.Value -> cups.sortedByDescending { it.finalScore }
                        is CupOrder.Favorite -> cups.sortedByDescending { it.favorite }
                    }

                }
            }
        }
    }
}