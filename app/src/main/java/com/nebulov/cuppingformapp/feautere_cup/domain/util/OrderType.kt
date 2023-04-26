package com.nebulov.cuppingformapp.feautere_cup.domain.util

sealed class OrderType {
    object Ascending: OrderType()
    object Descending: OrderType()
}