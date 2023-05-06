package com.nebulov.cuppingformapp.feature_cup.presentation.util

import java.text.SimpleDateFormat
import java.util.Date


fun convertLongToTime(time: Long): String {
    val date = Date(time)
    val format = SimpleDateFormat("dd.MM.yyyy")
    return format.format(date)
}