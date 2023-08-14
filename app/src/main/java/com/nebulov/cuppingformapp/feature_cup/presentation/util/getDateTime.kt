package com.nebulov.cuppingformapp.feature_cup.presentation.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


fun convertLongToTime(time: Long): String {
    val date = Date(time)
    return SimpleDateFormat("d MMMM yyyy", Locale.getDefault()).format(date)
}