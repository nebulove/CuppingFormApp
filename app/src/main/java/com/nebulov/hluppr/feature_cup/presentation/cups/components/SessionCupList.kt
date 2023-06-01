package com.nebulov.hluppr.feature_cup.presentation.cups.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarResult
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.nebulov.cuppingformapp.R
import com.nebulov.hluppr.feature_cup.presentation.cups.CupEvent
import com.nebulov.hluppr.feature_cup.presentation.cups.CupsState
import com.nebulov.hluppr.feature_cup.presentation.cups.CupsViewModel
import com.nebulov.hluppr.feature_cup.presentation.util.Screen
import com.nebulov.hluppr.feature_cup.presentation.util.convertLongToTime
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SessionCupList(
    modifier: Modifier = Modifier,
    navController: NavController,
    scrollState: LazyListState,
    paddingValues: PaddingValues,
    state: CupsState,
    viewModel: CupsViewModel = hiltViewModel(),
    scope: CoroutineScope,
    scaffoldState: ScaffoldState
) {

    val duplicatedValues = remember(state.cups) {
        state.cups.groupBy { it.timestamp }
            .filterValues { it.size > 1 }
            .keys
    }

    val filteredCups = remember(duplicatedValues, state.cups) {
        state.cups.filter { it.timestamp in duplicatedValues }
    }

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
                index == filteredCups.indexOf(filteredCups[0])|| cup.timestamp != filteredCups[index - 1].timestamp
            val lastItem =
                index == filteredCups.size - 1 || cup.timestamp != filteredCups[index + 1].timestamp

            if (showDate) {
                item {
                    Surface(
                        shape = RoundedCornerShape(
                            bottomEnd = 0.dp,
                            bottomStart = 0.dp,
                            topEnd = 8.dp,
                            topStart = 8.dp
                        ),
                        modifier = modifier
                            .wrapContentWidth()
                            .padding(
                                top = 8.dp,
                                start = 10.dp,
                                end = 10.dp,
                            )
                    )
                    {
                        Text(
                            text = convertLongToTime(cup.timestamp) + " Session #$index",
                            fontSize = 12.sp,
                            modifier = modifier
                                .padding(start = 10.dp, top = 6.dp, end = 10.dp)

                        )
                    }
                }
            }
            item {

                CupItem(
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
                        },
                    onDeleteClick = {
                        viewModel.onEvent(CupEvent.DeleteCup(cup))
                        scope.launch {
                            val result =
                                scaffoldState.snackbarHostState.showSnackbar(
                                    message = "Cup deleted",
                                    actionLabel = "Undo"
                                )
                            if (result == SnackbarResult.ActionPerformed) {
                                viewModel.onEvent(CupEvent.RestoreCup)
                            }
                        }
                    },
                    onFavoriteChange = {
                        viewModel.onEvent(
                            CupEvent.ChangeFavorite(
                                cup
                            )
                        )
                    },
                    icon = if (cup.favorite) R.drawable.baseline_water_drop_24 else R.drawable.outline_water_drop_black_24dp
                )
            }
        }
    }
}