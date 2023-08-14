package com.nebulov.cuppingformapp.feature_cup.domain.util

sealed class OrderType {
    object Ascending: OrderType()
    object Descending: OrderType()
}