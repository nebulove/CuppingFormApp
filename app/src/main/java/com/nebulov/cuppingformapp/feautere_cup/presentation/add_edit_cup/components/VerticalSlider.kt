package com.nebulov.cuppingformapp.feautere_cup.presentation.add_edit_cup.components

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Slider
import androidx.compose.material.SliderDefaults
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nebulov.cuppingform.core.Constants.Companion.ZERO_F
import com.nebulov.cuppingformapp.R
import com.nebulov.cuppingformapp.ui.theme.Dark
import com.nebulov.cuppingformapp.ui.theme.DarkNight
import com.nebulov.cuppingformapp.ui.theme.Light
import com.nebulov.cuppingformapp.ui.theme.LightNight
import com.nebulov.cuppingformapp.ui.theme.Medium0
import com.nebulov.cuppingformapp.ui.theme.Medium0Night
import com.nebulov.cuppingformapp.ui.theme.Medium1
import com.nebulov.cuppingformapp.ui.theme.Medium1Night
import com.nebulov.cuppingformapp.ui.theme.Medium2
import com.nebulov.cuppingformapp.ui.theme.Medium2Night
import com.nebulov.cuppingformapp.ui.theme.VeryDark
import com.nebulov.cuppingformapp.ui.theme.VeryDarkNight
import com.nebulov.cuppingformapp.ui.theme.VeryLight
import com.nebulov.cuppingformapp.ui.theme.VeryLightNight
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Composable
fun VerticalSlider(
    modifier: Modifier = Modifier,
    @StringRes text: Int,
    fragranceCheckSlider: Boolean = false,
    acidityCheckSlider: Boolean = false,
    bodyCheckSlider: Boolean = false,
    sliderValue: Float,
    sliderValue2: Float = ZERO_F,
    sliderValue3: Float = ZERO_F,
    onValueChange: (Float) -> Unit,
    onValueChange2: (Float) -> Unit,
    onValueChange3: (Float) -> Unit,
    textDescriptors: String,
    onTextChange: (String) -> Unit,
    scaffoldState: ScaffoldState,
    coroutineScope: CoroutineScope,
    context: Context,
    textInfo: Int,
) {

    fun onClickInfo(textInfo: Int) {
        coroutineScope.launch {
            scaffoldState.snackbarHostState.showSnackbar(
                message = context.getString(textInfo),
                duration = SnackbarDuration.Short
            )
        }
    }

//    fun sliderValueCut(value: MutableState<Float>): Float {
//        val cut = when (value.value) {
//            10f -> 10f
//            in 9.75f..10f -> 9.75f
//            in 9.5f..9.75f -> 9.50f
//            in 9.25f..9.5f -> 9.25f
//            in 9.0f..9.25f -> 9.00f
//            in 8.75f..9.0f -> 8.75f
//            in 8.5f..8.75f -> 8.50f
//            in 8.25f..8.5f -> 8.25f
//            in 8.0f..8.25f -> 8.00f
//            in 7.75f..8.0f -> 7.75f
//            in 7.5f..7.75f -> 7.50f
//            in 7.25f..7.5f -> 7.25f
//            in 7.0f..7.25f -> 7.00f
//            in 6.75f..7.0f -> 6.75f
//            in 6.5f..6.75f -> 6.50f
//            in 6.25f..6.5f -> 6.25f
//            in 6.0f..6.25f -> 6.00f
//            in 5.75f..6.0f -> 6.00f
//            else -> 6.00f
//        }
//        return cut
//    }

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
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = modifier
                .padding(
                    start = 16.dp,
                    end = 11.dp,
                    top = 4.dp
                )
                .fillMaxWidth()
        ) {
            Text(
                stringResource(id = text)
            )
            InfoIcon(
                modifier = modifier.align(Alignment.CenterVertically),
                onClickInfo = { onClickInfo(textInfo) })
            Spacer(modifier = modifier.weight(1f, true))
            Text(
                text = String.format("%.2f", sliderValue),
                fontSize = 18.sp
            )
        }
        Column(
            modifier = modifier
                .padding(
                    start = 8.dp,
                    end = 8.dp,
                    top = 26.dp,
                    bottom = 4.dp
                )
        ) {
            Slider(
                value = sliderValue,
                onValueChange = { onValueChange(it) },
                valueRange = 6f..10f,
                steps = 15,
                enabled = true,
                modifier = modifier.padding(bottom = 2.dp)
            )
            if (fragranceCheckSlider)
                Row(
                    modifier = modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    SmallSlider(
                        stringResource(R.string.Dry),
                        modifier = modifier,
                        sliderValue2,
                        onValueChange = { onValueChange2(it) }
                    )
                    SmallSlider(
                        stringResource(R.string.Break),
                        modifier = modifier,
                        sliderValue3,
                        onValueChange = { onValueChange3(it) }
                    )
                }
            if (acidityCheckSlider)
                Row(
                    modifier = modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    SmallSlider(
                        stringResource(R.string.Intensity),
                        modifier = modifier,
                        sliderValue2,
                        onValueChange = { onValueChange2(it) }
                    )
                }
            if (bodyCheckSlider)
                Row(
                    modifier = modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    SmallSlider(
                        stringResource(R.string.Level),
                        modifier = modifier,
                        sliderValue2,
                        onValueChange = { onValueChange2(it) }
                    )
                }
            NotesForm(
                modifier,
                textDescriptors = textDescriptors,
                onValueChange = { onTextChange(it) })
        }
    }
}

@Composable
fun SmallSlider(
    name: String,
    modifier: Modifier = Modifier,
    sliderValue: Float = ZERO_F,
    onValueChange: (Float) -> Unit
) {
    @Composable
    fun gradientChose(darkTheme: Boolean = isSystemInDarkTheme()): Color {
        val color = if (darkTheme) {
            when (sliderValue) {
                5f -> VeryDarkNight
                in 4.1f..5f -> DarkNight
                in 3.3f..4.1f -> Medium2Night
                in 2.5f..3.3f -> Medium1Night
                in 1.7f..2.5f -> Medium0Night
                in 0.9f..1.7f -> LightNight
                else -> VeryLightNight
            }
        } else {
            when (sliderValue) {
                5f -> VeryDark
                in 4.1f..5f -> Dark
                in 3.3f..4.1f -> Medium2
                in 2.5f..3.3f -> Medium1
                in 1.7f..2.5f -> Medium0
                in 0.9f..1.7f -> Light
                else -> VeryLight
            }
        }
        return color
    }


    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(modifier = modifier, text = name)
        Slider(
            value = sliderValue,
            onValueChange = { onValueChange(it) },
            steps = 3,
            enabled = true,
            valueRange = 0f..5f,
            modifier = modifier.width(160.dp),
            colors = SliderDefaults.colors(
                activeTrackColor = gradientChose(),
                thumbColor = gradientChose(),
                activeTickColor = contentColorFor(MaterialTheme.colors.primary)
                    .copy(alpha = SliderDefaults.TickAlpha)
            )
        )
    }
}