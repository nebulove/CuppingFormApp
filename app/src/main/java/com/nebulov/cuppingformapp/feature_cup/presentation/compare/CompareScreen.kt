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
                        TextForCompareItem(text = stringResource(id = R.string.Roast))
                        HorizonStripe()
                        TextForCompareItem(text = stringResource(id = R.string.Frag))
                        HorizonStripe()
                        TextForCompareItem(text = stringResource(id = R.string.Dry))
                        HorizonStripe()
                        TextForCompareItem(text = stringResource(id = R.string.Break))
                        HorizonStripe()
                        TextForCompareItem(text = stringResource(id = R.string.Flavor))
                        HorizonStripe()
                        TextForCompareItem(text = stringResource(id = R.string.Aftertaste))
                        HorizonStripe()
                        TextForCompareItem(text = stringResource(id = R.string.Acidity))
                        HorizonStripe()
                        TextForCompareItem(text = stringResource(id = R.string.Intensity))
                        HorizonStripe()
                        TextForCompareItem(text = stringResource(id = R.string.Body))
                        HorizonStripe()
                        TextForCompareItem(text = stringResource(id = R.string.Level))
                        HorizonStripe()
                        TextForCompareItem(text = stringResource(id = R.string.Balance))
                        HorizonStripe()
                        TextForCompareItem(text = stringResource(id = R.string.Uniformity))
                        HorizonStripe()
                        TextForCompareItem(text = stringResource(id = R.string.CleanCup))
                        HorizonStripe()
                        TextForCompareItem(text = stringResource(id = R.string.Sweetness))
                        HorizonStripe()
                        TextForCompareItem(text = stringResource(id = R.string.Defects))
                        HorizonStripe()
                        TextForCompareItem(text = stringResource(id = R.string.Overall))
                        HorizonStripe()
                        TextForCompareItem(text = stringResource(id = R.string.FinalScore))
                    }
                    VerticalStripe()
                }
                LazyRow(modifier = modifier) {
                    cupList.forEachIndexed { index, cup ->
                        item {
                            Row(modifier = modifier.height(IntrinsicSize.Min)) {
                                CompareItem(cup = cup, scrollState = scrollState)
                                VerticalStripe()
                            }
                        }
                    }
                }
            }
        }
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


