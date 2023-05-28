package com.nebulov.hluppr.feature_cup.domain.util

sealed class OrderType {
    object Ascending: OrderType()
    object Descending: OrderType()
}