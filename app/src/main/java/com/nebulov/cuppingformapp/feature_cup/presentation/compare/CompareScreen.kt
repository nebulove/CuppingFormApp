package com.nebulov.cuppingformapp.feature_cup.presentation.compare

import android.content.res.Configuration
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FloatingActionButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.nebulov.cuppingformapp.R
import com.nebulov.cuppingformapp.feature_cup.presentation.compare.components.CompareItem
import com.nebulov.cuppingformapp.feature_cup.presentation.compare.components.GradientButton
import com.nebulov.cuppingformapp.feature_cup.presentation.compare.components.TextForCompareItem
import com.nebulov.cuppingformapp.feature_cup.presentation.compare.components.TextForNameCompareItem
import com.nebulov.cuppingformapp.feature_cup.presentation.cups.CupsViewModel
import com.nebulov.cuppingformapp.feature_cup.presentation.cups.components.GradientFloatingActionButton

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CompareScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: CupsViewModel = hiltViewModel(),
    route: String
) {

    val list: List<String> = route.split(" ").toList()
    val state = viewModel.state.value
    val cupList = state.cups.filter { it.id.toString() in list }
    val scrollState = rememberScrollState()

    val visibilityRL = rememberSaveable { mutableStateOf(true) }
    val visibilityFR = rememberSaveable { mutableStateOf(true) }
    val visibilityDry = rememberSaveable { mutableStateOf(true) }
    val visibilityBreak = rememberSaveable { mutableStateOf(true) }
    val visibilityFlavor = rememberSaveable { mutableStateOf(true) }
    val visibilityAc = rememberSaveable { mutableStateOf(true) }
    val visibilityAf = rememberSaveable { mutableStateOf(true) }
    val visibilityInt = rememberSaveable { mutableStateOf(true) }
    val visibilityBody = rememberSaveable { mutableStateOf(true) }
    val visibilityLvl = rememberSaveable { mutableStateOf(true) }
    val visibilityBalance = rememberSaveable { mutableStateOf(true) }
    val visibilityUn = rememberSaveable { mutableStateOf(true) }
    val visibilityCC = rememberSaveable { mutableStateOf(true) }
    val visibilitySw = rememberSaveable { mutableStateOf(true) }
    val visibilityDefects = rememberSaveable { mutableStateOf(true) }
    val visibilityOverall = rememberSaveable { mutableStateOf(true) }
    val visibilityFS = rememberSaveable { mutableStateOf(true) }

    val colorRL = rememberSaveable { mutableStateOf(true) }
    val colorFR = rememberSaveable { mutableStateOf(true) }
    val colorDry = rememberSaveable { mutableStateOf(true) }
    val colorBreak = rememberSaveable { mutableStateOf(true) }
    val colorFlavor = rememberSaveable { mutableStateOf(true) }
    val colorAc = rememberSaveable { mutableStateOf(true) }
    val colorAf = rememberSaveable { mutableStateOf(true) }
    val colorInt = rememberSaveable { mutableStateOf(true) }
    val colorBody = rememberSaveable { mutableStateOf(true) }
    val colorLvl = rememberSaveable { mutableStateOf(true) }
    val colorBalance = rememberSaveable { mutableStateOf(true) }
    val colorUn = rememberSaveable { mutableStateOf(true) }
    val colorCC = rememberSaveable { mutableStateOf(true) }
    val colorSw = rememberSaveable { mutableStateOf(true) }
    val colorDefects = rememberSaveable { mutableStateOf(true) }
    val colorOverall = rememberSaveable { mutableStateOf(true) }
    val colorFS = rememberSaveable { mutableStateOf(true) }

    val gradientList = listOf(MaterialTheme.colors.primary, MaterialTheme.colors.secondary)

    val colorButtonState = rememberSaveable { mutableStateOf(true) }
    val hideButtonState = rememberSaveable { mutableStateOf(true) }

    fun colorChange() {
        if (!colorRL.value) {
            colorRL.value = !colorRL.value
        } else {
            colorRL.value = (viewModel.compareRL(cupList))
        }

        if (!colorFR.value) {
            colorFR.value = !colorFR.value
        } else {
            colorFR.value = (viewModel.compareFR(cupList))
        }

        if (!colorDry.value) {
            colorDry.value = !colorDry.value
        } else {
            colorDry.value = (viewModel.compareDry(cupList))
        }

        if (!colorBreak.value) {
            colorBreak.value = !colorBreak.value
        } else {
            colorBreak.value = (viewModel.compareBreak(cupList))
        }

        if (!colorFlavor.value) {
            colorFlavor.value = !colorFlavor.value
        } else {
            colorFlavor.value = (viewModel.compareFlavor(cupList))
        }

        if (!colorAc.value) {
            colorAc.value = !colorAc.value
        } else {
            colorAc.value = (viewModel.compareAc(cupList))
        }

        if (!colorAf.value) {
            colorAf.value = !colorAf.value
        } else {
            colorAf.value = (viewModel.compareAf(cupList))
        }

        if (!colorInt.value) {
            colorInt.value = !colorInt.value
        } else {
            colorInt.value = (viewModel.compareInt(cupList))
        }

        if (!colorBody.value) {
            colorBody.value = !colorBody.value
        } else {
            colorBody.value = (viewModel.compareBody(cupList))
        }

        if (!colorLvl.value) {
            colorLvl.value = !colorLvl.value
        } else {
            colorLvl.value = (viewModel.compareLvl(cupList))
        }

        if (!colorBalance.value) {
            colorBalance.value = !colorBalance.value
        } else {
            colorBalance.value = (viewModel.compareBalance(cupList))
        }

        if (!colorUn.value) {
            colorUn.value = !colorUn.value
        } else {
            colorUn.value = (viewModel.compareUn(cupList))
        }

        if (!colorCC.value) {
            colorCC.value = !colorCC.value
        } else {
            colorCC.value = (viewModel.compareCC(cupList))
        }

        if (!colorSw.value) {
            colorSw.value = !colorSw.value
        } else {
            colorSw.value = (viewModel.compareSw(cupList))
        }

        if (!colorDefects.value) {
            colorDefects.value = !colorDefects.value
        } else {
            colorDefects.value = (viewModel.compareDefects(cupList))
        }

        if (!colorOverall.value) {
            colorOverall.value = !colorOverall.value
        } else {
            colorOverall.value = (viewModel.compareOverall(cupList))
        }

        if (!colorFS.value) {
            colorFS.value = !colorFS.value
        } else {
            colorFS.value = (viewModel.compareFS(cupList))
        }
    }

    fun visibleChange() {
        if (!visibilityRL.value) {
            visibilityRL.value = !visibilityRL.value
        } else {
            visibilityRL.value = (viewModel.compareRL(cupList))
        }

        if (!visibilityFR.value) {
            visibilityFR.value = !visibilityFR.value
        } else {
            visibilityFR.value = (viewModel.compareFR(cupList))
        }

        if (!visibilityDry.value) {
            visibilityDry.value = !visibilityDry.value
        } else {
            visibilityDry.value = (viewModel.compareDry(cupList))
        }

        if (!visibilityBreak.value) {
            visibilityBreak.value = !visibilityBreak.value
        } else {
            visibilityBreak.value = (viewModel.compareBreak(cupList))
        }

        if (!visibilityFlavor.value) {
            visibilityFlavor.value = !visibilityFlavor.value
        } else {
            visibilityFlavor.value = (viewModel.compareFlavor(cupList))
        }

        if (!visibilityAc.value) {
            visibilityAc.value = !visibilityAc.value
        } else {
            visibilityAc.value = (viewModel.compareAc(cupList))
        }

        if (!visibilityAf.value) {
            visibilityAf.value = !visibilityAf.value
        } else {
            visibilityAf.value = (viewModel.compareAf(cupList))
        }

        if (!visibilityInt.value) {
            visibilityInt.value = !visibilityInt.value
        } else {
            visibilityInt.value = (viewModel.compareInt(cupList))
        }

        if (!visibilityBody.value) {
            visibilityBody.value = !visibilityBody.value
        } else {
            visibilityBody.value = (viewModel.compareBody(cupList))
        }

        if (!visibilityLvl.value) {
            visibilityLvl.value = !visibilityLvl.value
        } else {
            visibilityLvl.value = (viewModel.compareLvl(cupList))
        }

        if (!visibilityBalance.value) {
            visibilityBalance.value = !visibilityBalance.value
        } else {
            visibilityBalance.value = (viewModel.compareBalance(cupList))
        }

        if (!visibilityUn.value) {
            visibilityUn.value = !visibilityUn.value
        } else {
            visibilityUn.value = (viewModel.compareUn(cupList))
        }

        if (!visibilityCC.value) {
            visibilityCC.value = !visibilityCC.value
        } else {
            visibilityCC.value = (viewModel.compareCC(cupList))
        }

        if (!visibilitySw.value) {
            visibilitySw.value = !visibilitySw.value
        } else {
            visibilitySw.value = (viewModel.compareSw(cupList))
        }

        if (!visibilityDefects.value) {
            visibilityDefects.value = !visibilityDefects.value
        } else {
            visibilityDefects.value = (viewModel.compareDefects(cupList))
        }

        if (!visibilityOverall.value) {
            visibilityOverall.value = !visibilityOverall.value
        } else {
            visibilityOverall.value = (viewModel.compareOverall(cupList))
        }

        if (!visibilityFS.value) {
            visibilityFS.value = !visibilityFS.value
        } else {
            visibilityFS.value = (viewModel.compareFS(cupList))
        }

    }


    val configuration = LocalConfiguration.current
    when (configuration.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> {

            Row(
                modifier = modifier
                    .fillMaxSize()
                    .padding(
                        top = 3.dp,
                        start = 68.dp,
                        end = 6.dp
                    ),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    modifier = modifier

                ) {
                    Row(
                        modifier = modifier
                            .animateContentSize(
                                animationSpec = tween(
                                    durationMillis = 150,
                                    easing = LinearOutSlowInEasing
                                )
                            )
                    ) {
                        Row(
                            modifier = modifier
                                .height(IntrinsicSize.Min)
                        ) {
                            Column(
                                modifier = modifier
                                    .width(85.dp)
                                    .verticalScroll(scrollState)
                            ) {
                                TextForNameCompareItem(
                                    text = "Name:",
                                )
                                HorizonStripe()
                                if (visibilityRL.value) {
                                    TextForCompareItem(
                                        text = stringResource(id = R.string.Roast),
                                        state = colorRL.value
                                    )
                                    HorizonStripe()
                                }
                                if (visibilityFR.value) {
                                    TextForCompareItem(
                                        text = stringResource(id = R.string.Frag),
                                        state = colorFR.value
                                    )
                                    HorizonStripe()
                                }
                                if (visibilityDry.value) {
                                    TextForCompareItem(
                                        text = stringResource(id = R.string.Dry),
                                        state = colorDry.value
                                    )
                                    HorizonStripe()
                                }
                                if (visibilityBreak.value) {
                                    TextForCompareItem(
                                        text = stringResource(id = R.string.Break),
                                        state = colorBreak.value
                                    )
                                    HorizonStripe()
                                }
                                if (visibilityFlavor.value) {
                                    TextForCompareItem(
                                        text = stringResource(id = R.string.Flavor),
                                        state = colorFlavor.value
                                    )
                                    HorizonStripe()
                                }
                                if (visibilityAf.value) {
                                    TextForCompareItem(
                                        text = stringResource(id = R.string.Aftertaste),
                                        state = colorAf.value
                                    )
                                    HorizonStripe()
                                }
                                if (visibilityAc.value) {
                                    TextForCompareItem(
                                        text = stringResource(id = R.string.Acidity),
                                        state = colorAc.value
                                    )
                                    HorizonStripe()
                                }
                                if (visibilityInt.value) {
                                    TextForCompareItem(
                                        text = stringResource(id = R.string.Intensity),
                                        state = colorInt.value
                                    )
                                    HorizonStripe()
                                }
                                if (visibilityBody.value) {
                                    TextForCompareItem(
                                        text = stringResource(id = R.string.Body),
                                        state = colorBody.value
                                    )
                                    HorizonStripe()
                                }
                                if (visibilityLvl.value) {
                                    TextForCompareItem(
                                        text = stringResource(id = R.string.Level),
                                        state = colorLvl.value
                                    )
                                    HorizonStripe()
                                }
                                if (visibilityBalance.value) {
                                    TextForCompareItem(
                                        text = stringResource(id = R.string.Balance),
                                        state = colorBalance.value
                                    )
                                    HorizonStripe()
                                }
                                if (visibilityUn.value) {
                                    TextForCompareItem(
                                        text = stringResource(id = R.string.Uniformity),
                                        state = colorUn.value
                                    )
                                    HorizonStripe()
                                }
                                if (visibilityCC.value) {
                                    TextForCompareItem(
                                        text = stringResource(id = R.string.CleanCup),
                                        state = colorCC.value
                                    )
                                    HorizonStripe()
                                }
                                if (visibilitySw.value) {
                                    TextForCompareItem(
                                        text = stringResource(id = R.string.Sweetness),
                                        state = colorSw.value
                                    )
                                    HorizonStripe()
                                }
                                if (visibilityDefects.value) {
                                    TextForCompareItem(
                                        text = stringResource(id = R.string.Defects),
                                        state = colorDefects.value
                                    )
                                    HorizonStripe()
                                }
                                if (visibilityOverall.value) {
                                    TextForCompareItem(
                                        text = stringResource(id = R.string.Overall),
                                        state = colorOverall.value
                                    )
                                    HorizonStripe()
                                }
                                if (visibilityFS.value) {
                                    TextForCompareItem(
                                        text = stringResource(id = R.string.FinalScore),
                                        state = colorFS.value
                                    )
                                }
                            }
                            VerticalStripe()
                        }
                        LazyRow(modifier = modifier) {
                            cupList.forEachIndexed { index, cup ->
                                item {
                                    Row(
                                        modifier = modifier
                                            .height(IntrinsicSize.Min)
                                            .animateItemPlacement()
                                    ) {
                                        CompareItem(
                                            cup = cup,
                                            scrollState = scrollState,
                                            visibilityRL = visibilityRL,
                                            visibilityFR = visibilityFR,
                                            visibilityDry = visibilityDry,
                                            visibilityBreak = visibilityBreak,
                                            visibilityFlavor = visibilityFlavor,
                                            visibilityAc = visibilityAc,
                                            visibilityAf = visibilityAf,
                                            visibilityInt = visibilityInt,
                                            visibilityBody = visibilityBody,
                                            visibilityLvl = visibilityLvl,
                                            visibilityBalance = visibilityBalance,
                                            visibilityUn = visibilityUn,
                                            visibilityCC = visibilityCC,
                                            visibilitySw = visibilitySw,
                                            visibilityDefects = visibilityDefects,
                                            visibilityOverall = visibilityOverall,
                                            visibilityFS = visibilityFS,
                                            colorRL = colorRL,
                                            colorFR = colorFR,
                                            colorDry = colorDry,
                                            colorBreak = colorBreak,
                                            colorFlavor = colorFlavor,
                                            colorAc = colorAc,
                                            colorAf = colorAf,
                                            colorInt = colorInt,
                                            colorBody = colorBody,
                                            colorLvl = colorLvl,
                                            colorBalance = colorBalance,
                                            colorUn = colorUn,
                                            colorCC = colorCC,
                                            colorSw = colorSw,
                                            colorDefects = colorDefects,
                                            colorOverall = colorOverall,
                                            colorFS = colorFS,
                                        )
                                        VerticalStripe()
                                    }
                                }
                            }
                        }
                    }
                }
            }
            Column(
                modifier = modifier
                    .padding(start = 3.dp, top = 6.dp)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column(verticalArrangement = Arrangement.SpaceBetween) {
                    GradientFloatingActionButton(
                        text = "COLOR",
                        actionOn = { colorChange() },
                        iconOn = R.drawable.baseline_visibility_off_24,
                        iconOff = R.drawable.outline_visibility_24,
                        contentDescription = "",
                        enabled = colorButtonState
                    )
                    Spacer(modifier = modifier.height(8.dp))
                    GradientFloatingActionButton(
                        text = "HIDE",
                        actionOn = { visibleChange() },
                        iconOn = R.drawable.baseline_visibility_off_24,
                        iconOff = R.drawable.outline_visibility_24,
                        contentDescription = "",
                        enabled = hideButtonState
                    )
                }
                Spacer(modifier = modifier.height(8.dp))
                FloatingActionButton(
                    modifier=modifier.padding(start = 3.dp),
                    onClick = { navController.navigateUp() },
                    shape = RoundedCornerShape(50),
                    elevation = FloatingActionButtonDefaults.elevation(
                        defaultElevation = 0.dp,
                        pressedElevation = 0.dp,
                        hoveredElevation = 0.dp,
                        focusedElevation = 0.dp
                    ),
                    backgroundColor = Color.Transparent
                ) {
                    Icon(
                        painterResource(
                            R.drawable.outline_west_24
                        ),
                        contentDescription = stringResource(R.string.back),
                        modifier = modifier
                            .size(24.dp),
                        tint = MaterialTheme.colors.onPrimary
                    )
                }
            }
        }


        else -> {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(
                        top = 3.dp,
                        start = 6.dp,
                        end = 6.dp
                    ),
                verticalArrangement = Arrangement.SpaceBetween,
            ) {
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    modifier = modifier
                ) {
                    Row(
                        modifier = modifier.animateContentSize(
                            animationSpec = tween(
                                durationMillis = 150,
                                easing = LinearOutSlowInEasing
                            )
                        )
                    ) {
                        Row(
                            modifier = modifier
                                .height(IntrinsicSize.Min)
                        ) {
                            Column(
                                modifier = modifier
                                    .width(85.dp)
                                    .verticalScroll(scrollState),
                            ) {
                                TextForNameCompareItem(
                                    text = "Name:",
                                )
                                HorizonStripe()
                                if (visibilityRL.value) {
                                    TextForCompareItem(
                                        text = stringResource(id = R.string.Roast),
                                        state = colorRL.value
                                    )
                                    HorizonStripe()
                                }
                                if (visibilityFR.value) {
                                    TextForCompareItem(
                                        text = stringResource(id = R.string.Frag),
                                        state = colorFR.value
                                    )
                                    HorizonStripe()
                                }
                                if (visibilityDry.value) {
                                    TextForCompareItem(
                                        text = stringResource(id = R.string.Dry),
                                        state = colorDry.value
                                    )
                                    HorizonStripe()
                                }
                                if (visibilityBreak.value) {
                                    TextForCompareItem(
                                        text = stringResource(id = R.string.Break),
                                        state = colorBreak.value
                                    )
                                    HorizonStripe()
                                }
                                if (visibilityFlavor.value) {
                                    TextForCompareItem(
                                        text = stringResource(id = R.string.Flavor),
                                        state = colorFlavor.value
                                    )
                                    HorizonStripe()
                                }
                                if (visibilityAf.value) {
                                    TextForCompareItem(
                                        text = stringResource(id = R.string.Aftertaste),
                                        state = colorAf.value
                                    )
                                    HorizonStripe()
                                }
                                if (visibilityAc.value) {
                                    TextForCompareItem(
                                        text = stringResource(id = R.string.Acidity),
                                        state = colorAc.value
                                    )
                                    HorizonStripe()
                                }
                                if (visibilityInt.value) {
                                    TextForCompareItem(
                                        text = stringResource(id = R.string.Intensity),
                                        state = colorInt.value
                                    )
                                    HorizonStripe()
                                }
                                if (visibilityBody.value) {
                                    TextForCompareItem(
                                        text = stringResource(id = R.string.Body),
                                        state = colorBody.value
                                    )
                                    HorizonStripe()
                                }
                                if (visibilityLvl.value) {
                                    TextForCompareItem(
                                        text = stringResource(id = R.string.Level),
                                        state = colorLvl.value
                                    )
                                    HorizonStripe()
                                }
                                if (visibilityBalance.value) {
                                    TextForCompareItem(
                                        text = stringResource(id = R.string.Balance),
                                        state = colorBalance.value
                                    )
                                    HorizonStripe()
                                }
                                if (visibilityUn.value) {
                                    TextForCompareItem(
                                        text = stringResource(id = R.string.Uniformity),
                                        state = colorUn.value
                                    )
                                    HorizonStripe()
                                }
                                if (visibilityCC.value) {
                                    TextForCompareItem(
                                        text = stringResource(id = R.string.CleanCup),
                                        state = colorCC.value
                                    )
                                    HorizonStripe()
                                }
                                if (visibilitySw.value) {
                                    TextForCompareItem(
                                        text = stringResource(id = R.string.Sweetness),
                                        state = colorSw.value
                                    )
                                    HorizonStripe()
                                }
                                if (visibilityDefects.value) {
                                    TextForCompareItem(
                                        text = stringResource(id = R.string.Defects),
                                        state = colorDefects.value
                                    )
                                    HorizonStripe()
                                }
                                if (visibilityOverall.value) {
                                    TextForCompareItem(
                                        text = stringResource(id = R.string.Overall),
                                        state = colorOverall.value
                                    )
                                    HorizonStripe()
                                }
                                if (visibilityFS.value) {
                                    TextForCompareItem(
                                        text = stringResource(id = R.string.FinalScore),
                                        state = colorFS.value
                                    )
                                }
                            }
                            VerticalStripe()
                        }
                        LazyRow(modifier = modifier) {
                            cupList.forEachIndexed { index, cup ->
                                item {
                                    Row(
                                        modifier = modifier
                                            .height(IntrinsicSize.Min)
                                            .animateItemPlacement()
                                    ) {
                                        CompareItem(
                                            cup = cup,
                                            scrollState = scrollState,
                                            visibilityRL = visibilityRL,
                                            visibilityFR = visibilityFR,
                                            visibilityDry = visibilityDry,
                                            visibilityBreak = visibilityBreak,
                                            visibilityFlavor = visibilityFlavor,
                                            visibilityAc = visibilityAc,
                                            visibilityAf = visibilityAf,
                                            visibilityInt = visibilityInt,
                                            visibilityBody = visibilityBody,
                                            visibilityLvl = visibilityLvl,
                                            visibilityBalance = visibilityBalance,
                                            visibilityUn = visibilityUn,
                                            visibilityCC = visibilityCC,
                                            visibilitySw = visibilitySw,
                                            visibilityDefects = visibilityDefects,
                                            visibilityOverall = visibilityOverall,
                                            visibilityFS = visibilityFS,
                                            colorRL = colorRL,
                                            colorFR = colorFR,
                                            colorDry = colorDry,
                                            colorBreak = colorBreak,
                                            colorFlavor = colorFlavor,
                                            colorAc = colorAc,
                                            colorAf = colorAf,
                                            colorInt = colorInt,
                                            colorBody = colorBody,
                                            colorLvl = colorLvl,
                                            colorBalance = colorBalance,
                                            colorUn = colorUn,
                                            colorCC = colorCC,
                                            colorSw = colorSw,
                                            colorDefects = colorDefects,
                                            colorOverall = colorOverall,
                                            colorFS = colorFS,
                                        )
                                        VerticalStripe()
                                    }
                                }
                            }
                        }
                    }
                }
                Column(
                    verticalArrangement = Arrangement.SpaceBetween,
                    modifier = modifier.fillMaxWidth()
                ) {
                    Column(modifier = modifier.fillMaxWidth()) {
                        GradientButton(
                            gradientColors = gradientList,
                            text = "Change the color of repeating values",
                            onClick = { colorChange() },
                            enabled = colorButtonState
                        )
                        Spacer(modifier = modifier.height(8.dp))
                        GradientButton(
                            gradientColors = gradientList,
                            text = "Hide duplicate values",
                            onClick = { visibleChange() },
                            enabled = hideButtonState
                        )

                    }
                    Spacer(modifier = modifier.height(8.dp))

                    FloatingActionButton(
                        modifier=  modifier.padding(start = 2.dp),
                        onClick = { navController.navigateUp() },
                        shape = RoundedCornerShape(50),
                        elevation = FloatingActionButtonDefaults.elevation(
                            defaultElevation = 0.dp,
                            pressedElevation = 0.dp,
                            hoveredElevation = 0.dp,
                            focusedElevation = 0.dp
                        ),
                        backgroundColor = Color.Transparent
                    ) {
                        Icon(
                            painterResource(
                                R.drawable.outline_west_24
                            ),
                            contentDescription = stringResource(R.string.back),
                            modifier = modifier
                                .size(24.dp),
                            tint = MaterialTheme.colors.onPrimary
                        )
                    }
                }
            }
        }
    }


}

@Composable
fun HorizonStripe(modifier: Modifier = Modifier, visibility: Boolean = true) {
    if (visibility) {
        Surface(
            color = MaterialTheme.colors.primary,
            modifier = modifier
                .height(1.dp)
        ) {
            Spacer(modifier = modifier.fillMaxWidth())
        }
    }
}

@Composable
fun VerticalStripe(modifier: Modifier = Modifier) {
//    Surface(
//        color = MaterialTheme.colors.primary,
//        modifier = modifier
//            .width(1.dp)
//    ) {
//        Spacer(modifier = modifier.fillMaxHeight())
//    }

    Divider(
        color = MaterialTheme.colors.primary,
        modifier = modifier
            .fillMaxHeight(1f)
            .width(1.dp)
    )
}


