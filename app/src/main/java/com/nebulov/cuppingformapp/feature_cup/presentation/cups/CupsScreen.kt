package com.nebulov.cuppingformapp.feature_cup.presentation.cups

import android.annotation.SuppressLint
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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight.Companion.W200
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.nebulov.cuppingformapp.R
import com.nebulov.cuppingformapp.core.Constants.Companion.EMPTY_STRING
import com.nebulov.cuppingformapp.feature_cup.domain.util.CupOrder
import com.nebulov.cuppingformapp.feature_cup.presentation.add_edit_cup.AddEditCupEvent
import com.nebulov.cuppingformapp.feature_cup.presentation.add_edit_cup.AddEditCupViewModel
import com.nebulov.cuppingformapp.feature_cup.presentation.cups.components.AddCupTextField
import com.nebulov.cuppingformapp.feature_cup.presentation.cups.components.AddSessionTextField
import com.nebulov.cuppingformapp.feature_cup.presentation.cups.components.AnimationImage
import com.nebulov.cuppingformapp.feature_cup.presentation.cups.components.CompareButton
import com.nebulov.cuppingformapp.feature_cup.presentation.cups.components.CompareList
import com.nebulov.cuppingformapp.feature_cup.presentation.cups.components.CupListIconNavigation
import com.nebulov.cuppingformapp.feature_cup.presentation.cups.components.IconOrderSection
import com.nebulov.cuppingformapp.feature_cup.presentation.cups.components.SessionCupList
import com.nebulov.cuppingformapp.feature_cup.presentation.cups.components.SingleCupList
import com.nebulov.cuppingformapp.feature_cup.presentation.util.Screen
import com.nebulov.cuppingformapp.ui.theme.CuppingFormTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("MutableCollectionMutableState")
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
    val compareScrollState = rememberLazyListState()

    val fabVisible = remember { mutableStateOf(false) }

    val shownOrderIconBar = rememberSaveable() { mutableStateOf(false) }
    shownOrderIconBar.value = cupList.size >= 3

    val shownSetOrderIconBar = rememberSaveable() { mutableStateOf(false) }
    shownSetOrderIconBar.value = filteredCups.size >= 3

    val addEditCupViewModel: AddEditCupViewModel = hiltViewModel()

    val name = rememberSaveable { mutableStateOf(EMPTY_STRING) }
    val count = rememberSaveable { mutableStateOf(0) }

    val currentOnTimeout = rememberSaveable { mutableStateOf(false) }
    val showWallpaper = remember { mutableStateOf(false) }
    showWallpaper.value = cupList.isEmpty() && currentOnTimeout.value

    val showSessionWallpaper = remember { mutableStateOf(false) }
    showSessionWallpaper.value = filteredCups.isEmpty() && currentOnTimeout.value

    val selectedItemPosition = rememberSaveable { mutableStateOf(0) }

    val route: MutableState<MutableSet<String>> =
        rememberSaveable { mutableStateOf(mutableSetOf("")) }


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
            if (selectedItemPosition.value == 1) {
                IconOrderSection(
                    onOrderChange = { viewModel.onEvent(CupEvent.Order(it)) },
                    cupOrder = state.cupOrder,
                    shown = shownSetOrderIconBar,
                    visible = false
                )
            }
            if (selectedItemPosition.value == 2) {
                Text(
                    modifier = modifier
                        .fillMaxWidth()
                        .offset(y = 52.dp),
                    text = "click the button to",
                    textAlign = TextAlign.Center,
                    fontSize = 12.sp,
                    fontWeight = W200
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
                            scope.launch { sessionScrollState.animateScrollToItem(1) }
                            count.value = 0
                        })
                }
                if (selectedItemPosition.value == 2) {
                    CompareButton(
                        onClick = {
                            if (route.value.size != 1 && route.value.size != 2) {
                                navController.navigate(
                                    Screen.CompareScreen.route +
                                            "?route=${route.value.joinToString(" ")}"
                                )
                            }
                        },
                    )
                }
                CupListIconNavigation(selectedItemPosition = selectedItemPosition,
                    changeOrder = {
                        viewModel.onEvent(CupEvent.Order(CupOrder.Date(state.cupOrder.orderType)))
                    })
                if (selectedItemPosition.value == 0) {
                    AnimationImage(
                        shown = showWallpaper,
                        image3 = R.drawable.image03_day_v2,
                        image1Day = R.drawable.image01_day_v2,
                        image2 = R.drawable.image02_day_v2,
                        image1Night = R.drawable.image01_night_v2,
                        text1 = R.string.let_the_cupping_begin,
                        text2 = R.string.click_on_the_button_to_start_cupping
                    )
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
                    AnimationImage(
                        shown = showSessionWallpaper,
                        image3 = R.drawable.image03_day_v3_session,
                        image1Day = R.drawable.image01_day_v2_session,
                        image2 = R.drawable.image02_day_v2_session,
                        image1Night = R.drawable.image01_night_v2_session,
                        text1 = R.string.start_the_cupping_experience,
                        text2 = R.string.create_flight
                    )
                    SessionCupList(
                        navController = navController,
                        scrollState = sessionScrollState,
                        paddingValues = it,
                        filteredCups = filteredCups,
                    )
                }
                if (selectedItemPosition.value == 2) {
                    CompareList(
                        navController = navController,
                        scrollState = compareScrollState,
                        paddingValues = it,
                        cupList = state.cups,
                        route = route,
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}





