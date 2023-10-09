package com.nebulov.cuppingformapp.feature_cup.presentation.cups.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.nebulov.cuppingformapp.R
import com.nebulov.cuppingformapp.feature_cup.domain.model.Cup
import com.nebulov.cuppingformapp.feature_cup.presentation.util.convertLongToTime

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CompareList(
    modifier: Modifier = Modifier,
    navController: NavController,
    scrollState: LazyListState,
    paddingValues: PaddingValues,
    cupList: List<Cup>,
) {

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(paddingValues),
        state = scrollState,
        contentPadding = PaddingValues(
            bottom = 72.dp
        )
    ) {
        cupList.forEachIndexed { index, cup ->
            val showDate =
                index == 0 || convertLongToTime(cup.timestamp) != convertLongToTime(
                    cupList[index - 1].timestamp
                )
            val lastItem =
                index == cupList.size - 1 || cup.timestamp != cupList[index + 1].timestamp

            val firstItem =  index == 0 || cup.timestamp != cupList[index - 1].timestamp

            if (showDate) {
                item {
                    Text(
                        text = convertLongToTime(cup.timestamp),
                        fontSize = 12.sp,
                        modifier = modifier
                            .padding(start = 10.dp, top = 6.dp)

                    )
                }
            }
            item {
                CompareItem(
                    roundedCornerShape = RoundedCornerShape(
                        bottomEnd = if (lastItem) 8.dp else 0.dp,
                        bottomStart = if (lastItem) 8.dp else 0.dp,
                        topEnd = if (showDate || firstItem ) 8.dp else 0.dp,
                        topStart = if (showDate || firstItem) 8.dp else 0.dp,
                    ),
                    top = if (showDate) 8.dp else 0.dp,
                    bottom = if (lastItem) 8.dp else 0.dp,
                    cup = cup,
                    modifier = modifier
                        .animateItemPlacement(),
                    icon = if (cup.favorite)
                        R.drawable.baseline_water_drop_24
                    else
                        R.drawable.outline_water_drop_black_24dp
                )
            }
        }
    }
}