package com.nebulov.cuppingformapp.feature_cup.presentation.compare

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.nebulov.cuppingformapp.R
import com.nebulov.cuppingformapp.feature_cup.presentation.compare.components.CompareItem
import com.nebulov.cuppingformapp.feature_cup.presentation.cups.CupsViewModel

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

    Surface(
        shape = RoundedCornerShape(8.dp),
        modifier = modifier
            .padding(
                top = 3.dp,
                start = 6.dp,
                end = 6.dp,
                bottom = 3.dp
            )
    ) {
        Row {
            Column(
                modifier = modifier
                    .padding(
                        top = 3.dp,
                        start = 6.dp,
                        bottom = 3.dp
                    )
                    .width(85.dp)
            ) {
                Text(text = "Name:")
                VerticalStripe()
                Text(text = stringResource(id = R.string.Roast))
                Text(text = stringResource(id = R.string.Frag))
                Text(text = stringResource(id = R.string.Dry))
                Text(text = stringResource(id = R.string.Break))
                Text(text = stringResource(id = R.string.Flavor))
                Text(text = stringResource(id = R.string.Aftertaste))
                Text(text = stringResource(id = R.string.Acidity))
                Text(text = stringResource(id = R.string.Intensity))
                Text(text = stringResource(id = R.string.Body))
                Text(text = stringResource(id = R.string.Level))
                Text(text = stringResource(id = R.string.Balance))
                Text(text = stringResource(id = R.string.Uniformity))
                Text(text = stringResource(id = R.string.CleanCup))
                Text(text = stringResource(id = R.string.Sweetness))
                Text(text = stringResource(id = R.string.Defects))
                Text(text = stringResource(id = R.string.Overall))
                Text(text = stringResource(id = R.string.FinalScore))
            }
            LazyRow(modifier = modifier) {
                cupList.forEachIndexed { index, cup ->
                    item {
                        CompareItem(cup = cup, scrollState = scrollState)
                    }
                }
            }
        }
    }


}

@Composable
fun VerticalStripe(modifier: Modifier = Modifier) {
    Surface(
        color = MaterialTheme.colors.primary,
        modifier = modifier
            .height(1.dp)
    ) {
        Spacer(modifier = modifier.fillMaxWidth())
    }
}

