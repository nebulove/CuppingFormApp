package com.nebulov.cuppingformapp.feature_cup.presentation.cups.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarResult
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.nebulov.cuppingformapp.R
import com.nebulov.cuppingformapp.feature_cup.domain.model.Cup
import com.nebulov.cuppingformapp.feature_cup.presentation.cups.CupEvent
import com.nebulov.cuppingformapp.feature_cup.presentation.cups.CupsViewModel
import com.nebulov.cuppingformapp.feature_cup.presentation.util.Screen
import com.nebulov.cuppingformapp.feature_cup.presentation.util.convertLongToTime
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SingleCupList(
    modifier: Modifier = Modifier,
    navController: NavController,
    scrollState: LazyListState,
    paddingValues: PaddingValues,
    cupList: List<Cup>,
    viewModel: CupsViewModel = hiltViewModel(),
    scope: CoroutineScope,
    scaffoldState: ScaffoldState
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
                CupItem(
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
                    icon = if (cup.favorite)
                        R.drawable.baseline_water_drop_24
                    else
                        R.drawable.outline_water_drop_black_24dp
                )
            }
        }
    }
}