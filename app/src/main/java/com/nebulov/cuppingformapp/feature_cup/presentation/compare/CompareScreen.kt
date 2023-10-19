package com.nebulov.cuppingformapp.feature_cup.presentation.compare

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.nebulov.cuppingformapp.R
import com.nebulov.cuppingformapp.feature_cup.presentation.compare.components.CompareItem
import com.nebulov.cuppingformapp.feature_cup.presentation.compare.components.TextForCompareItem
import com.nebulov.cuppingformapp.feature_cup.presentation.cups.CupsViewModel
import com.nebulov.cuppingformapp.feature_cup.presentation.cups.components.DefaultFloatingActionButton

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




    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(
                top = 3.dp,
                start = 6.dp,
                end = 6.dp,
                bottom = 3.dp
            ),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Surface(
            shape = RoundedCornerShape(8.dp),
            modifier = modifier

        ) {
            Row() {
                Row(modifier = modifier.height(IntrinsicSize.Min)) {
                    Column(
                        modifier = modifier
                            .width(85.dp)
                            .verticalScroll(scrollState)
                    ) {
                        TextForCompareItem(text = "Name:")
                        HorizonStripe()
                        if (visibilityRL.value) {
                            TextForCompareItem(text = stringResource(id = R.string.Roast))
                            HorizonStripe()
                        }
                        if (visibilityFR.value) {
                            TextForCompareItem(text = stringResource(id = R.string.Frag))
                            HorizonStripe()
                        }
                        if (visibilityDry.value) {
                            TextForCompareItem(text = stringResource(id = R.string.Dry))
                            HorizonStripe()
                        }
                        if (visibilityBreak.value) {
                            TextForCompareItem(text = stringResource(id = R.string.Break))
                            HorizonStripe()
                        }
                        if (visibilityFlavor.value) {
                            TextForCompareItem(text = stringResource(id = R.string.Flavor))
                            HorizonStripe()
                        }
                        if (visibilityAf.value) {
                            TextForCompareItem(text = stringResource(id = R.string.Aftertaste))
                            HorizonStripe()
                        }
                        if (visibilityAc.value) {
                            TextForCompareItem(text = stringResource(id = R.string.Acidity))
                            HorizonStripe()
                        }
                        if (visibilityInt.value) {
                            TextForCompareItem(text = stringResource(id = R.string.Intensity))
                            HorizonStripe()
                        }
                        if (visibilityBody.value) {
                            TextForCompareItem(text = stringResource(id = R.string.Body))
                            HorizonStripe()
                        }
                        if (visibilityLvl.value) {
                            TextForCompareItem(text = stringResource(id = R.string.Level))
                            HorizonStripe()
                        }
                        if (visibilityBalance.value) {
                            TextForCompareItem(text = stringResource(id = R.string.Balance))
                            HorizonStripe()
                        }
                        if (visibilityUn.value) {
                            TextForCompareItem(text = stringResource(id = R.string.Uniformity))
                            HorizonStripe()
                        }
                        if (visibilityCC.value) {
                            TextForCompareItem(text = stringResource(id = R.string.CleanCup))
                            HorizonStripe()
                        }
                        if (visibilitySw.value) {
                            TextForCompareItem(text = stringResource(id = R.string.Sweetness))
                            HorizonStripe()
                        }
                        if (visibilityDefects.value) {
                            TextForCompareItem(text = stringResource(id = R.string.Defects))
                            HorizonStripe()
                        }
                        if (visibilityOverall.value) {
                            TextForCompareItem(text = stringResource(id = R.string.Overall))
                            HorizonStripe()
                        }
                        if (visibilityFS.value) {
                            TextForCompareItem(text = stringResource(id = R.string.FinalScore))
                        }
                    }
                    VerticalStripe()
                }
                LazyRow(modifier = modifier) {
                    cupList.forEachIndexed { index, cup ->
                        item {
                            Row(modifier = modifier.height(IntrinsicSize.Min)) {
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
                                    visibilityFS = visibilityFS
                                )
                                VerticalStripe()
                            }
                        }
                    }
                }
            }
        }
        DefaultFloatingActionButton(
            icon = R.drawable.baseline_visibility_off_24,
            actionOn = {
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

            },
            contentDescription = stringResource(R.string.back),
            shape = RoundedCornerShape(50)
        )

        DefaultFloatingActionButton(
            icon = R.drawable.outline_west_24,
            actionOn = {
                navController.navigateUp()
            },
            contentDescription = stringResource(R.string.back),
            shape = RoundedCornerShape(50)
        )
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


