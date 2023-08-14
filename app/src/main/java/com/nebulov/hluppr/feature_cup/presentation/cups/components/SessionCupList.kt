package com.nebulov.hluppr.feature_cup.presentation.cups.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.nebulov.hluppr.feature_cup.domain.model.Cup
import com.nebulov.hluppr.feature_cup.presentation.cups.CupEvent
import com.nebulov.hluppr.feature_cup.presentation.cups.CupsViewModel
import com.nebulov.hluppr.feature_cup.presentation.util.Screen
import com.nebulov.hluppr.feature_cup.presentation.util.convertLongToTime

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SessionCupList(
    modifier: Modifier = Modifier,
    navController: NavController,
    scrollState: LazyListState,
    paddingValues: PaddingValues,
    filteredCups: List<Cup>,
    viewModel: CupsViewModel = hiltViewModel()
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
        filteredCups.forEachIndexed { index, cup ->
            val showDate =
                index == filteredCups.indexOf(filteredCups[0]) || cup.timestamp != filteredCups[index - 1].timestamp
            val lastItem =
                index == filteredCups.size - 1 || cup.timestamp != filteredCups[index + 1].timestamp

            if (showDate) {
                item(key = cup.id) {
                    CupSessionTitleItem(
                        modifier = modifier.animateItemPlacement(),
                        date = convertLongToTime(cup.timestamp),
                        onDeleteClick = { viewModel.onEvent(CupEvent.DeleteSession(cup.timestamp)) },
                    )
                }
            }
            item {
                CupSessionItem(
                    roundedCornerShape = RoundedCornerShape(
                        bottomEnd = if (lastItem) 8.dp else 0.dp,
                        bottomStart = if (lastItem) 8.dp else 0.dp,
                        topEnd = if (showDate) 8.dp else 0.dp,
                        topStart = 0.dp
                    ),
                    top = if (showDate) 0.dp else 0.dp,
                    bottom = if (showDate) 0.dp else 0.dp,
                    cup = cup,
                    modifier = modifier
                        .animateItemPlacement()
                        .clickable {
                            navController.navigate(
                                Screen.AddEditCupScreen.route +
                                        "?cupId=${cup.id}"
                            )
                        }
                )
            }
        }
    }
}


