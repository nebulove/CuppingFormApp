package com.nebulov.cuppingformapp.feautere_cup.domain.util

sealed class CupOrder(val orderType: OrderType){

    class Title(orderType: OrderType) : CupOrder(orderType)

    class Date(orderType: OrderType) : CupOrder(orderType)

    class Value(orderType: OrderType) : CupOrder(orderType)

    fun copy(orderType: OrderType): CupOrder {
        return when (this) {
            is Title -> Title(orderType)
            is Date -> Date(orderType)
            is Value -> Value(orderType)
        }
    }
}
