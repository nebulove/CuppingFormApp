package com.nebulov.hluppr.feature_cup.presentation.cups

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.nebulov.cuppingformapp.R
import com.nebulov.hluppr.core.Constants.Companion.EMPTY_STRING
import com.nebulov.hluppr.feature_cup.domain.util.CupOrder
import com.nebulov.hluppr.feature_cup.presentation.add_edit_cup.AddEditCupEvent
import com.nebulov.hluppr.feature_cup.presentation.add_edit_cup.AddEditCupViewModel
import com.nebulov.hluppr.feature_cup.presentation.cups.components.AddCupTextField
import com.nebulov.hluppr.feature_cup.presentation.cups.components.AddSessionTextField
import com.nebulov.hluppr.feature_cup.presentation.cups.components.AnimationImage
import com.nebulov.hluppr.feature_cup.presentation.cups.components.CupListIconNavigation
import com.nebulov.hluppr.feature_cup.presentation.cups.components.IconOrderSection
import com.nebulov.hluppr.feature_cup.presentation.cups.components.SessionCupList
import com.nebulov.hluppr.feature_cup.presentation.cups.components.SingleCupList
import com.nebulov.hluppr.ui.theme.CuppingFormTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CupsScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: CupsViewModel = hiltViewModel(),
) {
    val state = viewModel.state.value
    val cupList = state.cups.groupBy { it.timestamp }.filterValues { it.size == 1 }.values.flatten()
    val duplicatedValues = remember(state.cups) {
        state.cups.groupBy { it.timestamp }
            .filterValues { it.size > 1 }
            .keys
    }
    val filteredCups = remember(duplicatedValues, state.cups) {
        state.cups.filter { it.timestamp in duplicatedValues }
    }

    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    val singleScrollState = rememberLazyListState()
    val sessionScrollState = rememberLazyListState()

    val fabVisible = remember { mutableStateOf(false) }

    val shownOrderIconBar = rememberSaveable() { mutableStateOf(false) }
    shownOrderIconBar.value = state.cups.size >= 3

    val addEditCupViewModel: AddEditCupViewModel = hiltViewModel()

    val name = rememberSaveable { mutableStateOf(EMPTY_STRING) }
    val count = rememberSaveable { mutableStateOf(0) }

    val currentOnTimeout = rememberSaveable { mutableStateOf(false) }
    val showWallpaper = remember { mutableStateOf(false) }
    showWallpaper.value = state.cups.isEmpty() && currentOnTimeout.value

    val selectedItemPosition = rememberSaveable { mutableStateOf(0) }

    LaunchedEffect(key1 = true) {
        delay(500)
        currentOnTimeout.value = true
    }

    LaunchedEffect(singleScrollState) {
        var prev = 0
        snapshotFlow { singleScrollState.firstVisibleItemIndex }
            .collect { index ->
                fabVisible.value = singleScrollState.firstVisibleItemIndex <= prev && index > 0
                prev = singleScrollState.firstVisibleItemIndex
            }
    }

    LaunchedEffect(sessionScrollState) {
        var prev = 0
        snapshotFlow { sessionScrollState.firstVisibleItemIndex }
            .collect { index ->
                fabVisible.value = sessionScrollState.firstVisibleItemIndex <= prev && index > 0
                prev = sessionScrollState.firstVisibleItemIndex
            }
    }

    CuppingFormTheme() {
        Scaffold(
            modifier = modifier,
            scaffoldState = scaffoldState,
            backgroundColor = MaterialTheme.colors.primary,
            floatingActionButton = {
                AnimatedVisibility(
                    visible = fabVisible.value,
                    enter = slideInVertically(initialOffsetY = { it / 2 }),
                    exit = slideOutVertically() + fadeOut()
                ) {
                    if (selectedItemPosition.value == 0) {
                        FloatingActionButton(
                            onClick = {
                                scope.launch { singleScrollState.animateScrollToItem(0) }
                            },
                            backgroundColor = MaterialTheme.colors.onPrimary,
                            modifier = modifier.size(38.dp)
                        ) {
                            Icon(
                                Icons.Filled.KeyboardArrowUp,
                                contentDescription = stringResource(R.string.backtothetopofthelist),
                                tint = MaterialTheme.colors.primary,
                                modifier = modifier
                                    .wrapContentSize()
                                    .fillMaxSize()
                                    .size(36.dp)
                            )
                        }
                    }
                    if (selectedItemPosition.value == 1) {
                        FloatingActionButton(
                            onClick = {
                                scope.launch { sessionScrollState.animateScrollToItem(0) }
                            },
                            backgroundColor = MaterialTheme.colors.onPrimary,
                            modifier = modifier.size(38.dp)
                        ) {
                            Icon(
                                Icons.Filled.KeyboardArrowUp,
                                contentDescription = stringResource(R.string.backtothetopofthelist),
                                tint = MaterialTheme.colors.primary,
                                modifier = modifier
                                    .wrapContentSize()
                                    .fillMaxSize()
                                    .size(36.dp)
                            )
                        }
                    }
                }
            }
        ) {
            if (selectedItemPosition.value == 0) {
                IconOrderSection(
                    onOrderChange = { viewModel.onEvent(CupEvent.Order(it)) },
                    cupOrder = state.cupOrder,
                    shown = shownOrderIconBar
                )
            }
            Column(modifier = modifier.animateContentSize(animationSpec = tween(500))) {
                Spacer(modifier = modifier.height(50.dp))
                if (selectedItemPosition.value == 0) {
                    AddCupTextField(
                        name = name,
                        onValueChange = { name.value = it },
                        addNewCup = {
                            addEditCupViewModel.onEvent(
                                AddEditCupEvent.SaveCupWithName(
                                    name.value,
                                    state.cups.size
                                )
                            )
                            name.value = EMPTY_STRING
                        })
                }
                if (selectedItemPosition.value == 1) {
                    AddSessionTextField(
                        count = count,
                        onValueChange = { count.value = it },
                        addNewCup = {
                            addEditCupViewModel.onEvent(
                                AddEditCupEvent.SaveSession(count.value)
                            )
                            scope.launch { sessionScrollState.animateScrollToItem(0) }
                            count.value = 0
                        })
                }
                CupListIconNavigation(selectedItemPosition = selectedItemPosition,
                    changeOrder = {
                        viewModel.onEvent(CupEvent.Order(CupOrder.Date(state.cupOrder.orderType)))
                    })
                AnimationImage(shown = showWallpaper)
                if (selectedItemPosition.value == 0) {
                    SingleCupList(
                        navController = navController,
                        scrollState = singleScrollState,
                        paddingValues = it,
                        cupList = cupList,
                        scope = scope,
                        scaffoldState = scaffoldState
                    )
                }
                if (selectedItemPosition.value == 1) {
                    SessionCupList(
                        navController = navController,
                        scrollState = sessionScrollState,
                        paddingValues = it,
                        filteredCups = filteredCups,
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}




