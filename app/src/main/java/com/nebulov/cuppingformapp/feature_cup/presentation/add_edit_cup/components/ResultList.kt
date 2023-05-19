package com.nebulov.cuppingformapp.feature_cup.presentation.add_edit_cup.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.nebulov.cuppingformapp.R
import com.nebulov.cuppingformapp.feature_cup.presentation.util.convertLongToTime
import com.nebulov.cuppingformapp.ui.theme.BWCuppingFormTheme

@Composable
fun ResultList(
    modifier: Modifier = Modifier,
    nameState: State<String>,
    levelOfRoast: State<Float>,
    fragrance: State<Float>,
    dry: State<Float>,
    breakAroma: State<Float>,
    flavor: State<Float>,
    aftertaste: State<Float>,
    acidity: State<Float>,
    intensity: State<Float>,
    body: State<Float>,
    levelOfBody: State<Float>,
    balance: State<Float>,
    uniformity: State<Int>,
    cleanCup: State<Int>,
    sweetness: State<Int>,
    defects: State<Int>,
    taintDefects: State<Int>,
    faultDefects: State<Int>,
    overall: State<Float>,
    finalScore: State<Float>,
    notesFragrance: State<String>,
    notesFlavor: State<String>,
    notesAftertaste: State<String>,
    notesAcidity: State<String>,
    notesBody: State<String>,
    notesBalance: State<String>,
    notesUniformity: State<String>,
    notesCleanCup: State<String>,
    notesSweetness: State<String>,
    notesDefects: State<String>,
    notesOverall: State<String>,
    onClick: () -> Unit,
    timestamp: State<Long>


) {
    fun roastLevel(value: Float): Int {
        val result = when (value) {
            6f -> R.string.VeryDarkRoast
            in 5f..6f -> R.string.DarkRoast
            in 4f..5f -> R.string.MediumDarkRoast
            in 3f..4f -> R.string.MediumRoast
            in 2f..3f -> R.string.MediumLightRoast
            in 1f..2f -> R.string.LightRoast
            else -> R.string.VeryLightRoast
        }
        return result
    }

    val scrollState = rememberScrollState()


    BWCuppingFormTheme {
        Surface(
            modifier = modifier
                .wrapContentSize()
                .fillMaxSize()
        ) {
            Icon(
                        painter = painterResource(R.drawable.logo_circle_3),
                contentDescription = null,
                modifier = modifier
                    .fillMaxHeight(0.6f),
                tint = MaterialTheme.colors.onPrimary.copy(0.01f)
            )
            Column(
                Modifier
                    .padding(8.dp)
                    .wrapContentSize()
                    .fillMaxSize()
                    .clickable(onClick =
                    { onClick() })
                    .verticalScroll(scrollState)
            ) {
                Text( "${convertLongToTime(timestamp.value)} ${nameState.value}")
                Text(
                    text = stringResource(id = R.string.Roast) + ": "
                            + stringResource(id = roastLevel(levelOfRoast.value))
                )
                Text(
                    stringResource(id = R.string.Fragrance) + ": "
                            + "${fragrance.value}"
                )
                Text(
                    stringResource(id = R.string.Dry) + ": "
                            + "${dry.value.toInt()} out of 5"
                )
                Text(
                    stringResource(id = R.string.Break) + ": "
                            + "${breakAroma.value.toInt()} out of 5"
                )
                if (notesFragrance.value != "") {
                    Text(notesFragrance.value)
                }
                Text(
                    stringResource(id = R.string.Flavor) + ": "
                            + "${flavor.value}"
                )
                if (notesFlavor.value != "") {
                    Text(notesFlavor.value)
                }
                Text(
                    stringResource(id = R.string.Aftertaste) + ": "
                            + "${aftertaste.value}"
                )
                if (notesAftertaste.value != "") {
                    Text(notesAftertaste.value)
                }
                Text(
                    stringResource(id = R.string.Acidity) + ": "
                            + "${acidity.value}"
                )
                Text(
                    stringResource(id = R.string.Intensity) + ": "
                            + "${intensity.value.toInt()} out of 5"
                )
                if (notesAcidity.value != "") {
                    Text(notesAcidity.value)
                }
                Text(
                    stringResource(id = R.string.Body) + ": "
                            + "${body.value}"
                )
                Text(
                    stringResource(id = R.string.Level) + ": "
                            + "${levelOfBody.value.toInt()} out of 5"
                )
                if (notesBody.value != "") {
                    Text(notesBody.value)
                }
                Text(
                    stringResource(id = R.string.Balance) + ": "
                            + "${balance.value}"
                )
                if (notesBalance.value != "") {
                    Text(notesBalance.value)
                }
                Text(
                    stringResource(id = R.string.Uniformity) + ": "
                            + "${uniformity.value}"
                )
                if (notesUniformity.value != "") {
                    Text(notesUniformity.value)
                }
                Text(
                    stringResource(id = R.string.CleanCup) + ": "
                            + "${cleanCup.value}"
                )
                if (notesCleanCup.value != "") {
                    Text(notesCleanCup.value)
                }
                Text(
                    stringResource(id = R.string.Sweetness) + ": "
                            + "${sweetness.value}"
                )
                if (notesSweetness.value != "") {
                    Text(notesSweetness.value)
                }
                Text(
                    stringResource(id = R.string.Defects) + ": "
                            + "${defects.value}"
                )
                if (defects.value != 0) {
                    Text(
                        stringResource(id = R.string.Taint) + ": "
                                + "${taintDefects.value}"
                    )
                    Text(
                        stringResource(id = R.string.Fault) + ": "
                                + "${faultDefects.value}"
                    )
                }
                if (notesDefects.value != "") {
                    Text(notesDefects.value)
                }
                Text(
                    stringResource(id = R.string.Overall) + ": "
                            + "${overall.value}"
                )
                if (notesOverall.value != "") {
                    Text(notesOverall.value)
                }
                Text(
                    stringResource(id = R.string.FinalScore) + ": "
                            + "${finalScore.value}"
                )
            }
        }
    }
}