package com.nebulov.hluppr.feature_cup.presentation.cups

import com.nebulov.cuppingformapp.R

sealed class NavigationIcon(
    val titleResId: Int,
    val iconRes: Int,
){
    object Single: NavigationIcon(
        titleResId = R.string.singleSample,
        iconRes = R.drawable.drop_plus_2_24dp
    )
    object Session: NavigationIcon(
        titleResId = R.string.set,
        iconRes = R.drawable.drop_plus_2_24dp
    )
    object Custom: NavigationIcon(
        titleResId = R.string.custom,
        iconRes = R.drawable.drop_plus_2_24dp
    )
}
