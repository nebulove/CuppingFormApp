package com.nebulov.cuppingformapp.feautere_cup.presentation.add_edit_cup.components

import androidx.annotation.StringRes
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Slider
import androidx.compose.material.SliderDefaults
import androidx.compose.material.Surface
import androidx.compose.material.contentColorFor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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


@Composable
fun RoastLevelSlider(
    modifier: Modifier = Modifier,
    levelOfRoast: Float,
    onValueChange:(Float)-> Unit
) {

    @Composable
    fun gradientChose(darkTheme: Boolean = isSystemInDarkTheme()): Color {
        val color = if (darkTheme) {
            when (levelOfRoast) {
                6f -> VeryDarkNight
                in 5f..6f -> DarkNight
                in 4f..5f -> Medium2Night
                in 3f..4f -> Medium1Night
                in 2f..3f -> Medium0Night
                in 1f..2f -> LightNight
                else -> VeryLightNight
            }
        } else {
            when (levelOfRoast) {
                6f -> VeryDark
                in 5f..6f -> Dark
                in 4f..5f -> Medium2
                in 3f..4f -> Medium1
                in 2f..3f -> Medium0
                in 1f..2f -> Light
                else -> VeryLight
            }
        }
        return color
    }
    //            when (sliderValue.value) {
//                6f -> CoffeeVeryDark
//                in 5f..6f -> CoffeeDark
//                in 4f..5f -> CoffeeMedium2
//                in 3f..4f -> CoffeeMedium1
//                in 2f..3f -> CoffeeMedium0
//                in 1f..2f -> CoffeeLight
//                else -> CoffeeVeryLight
    Slider(
        value = levelOfRoast,
        onValueChange = { onValueChange(it) },
        valueRange = 0f..6f,
        steps = 5,
        enabled = true,
        modifier = modifier.padding(bottom = 2.dp),
        colors = SliderDefaults.colors(
            activeTrackColor = gradientChose(),
            thumbColor = gradientChose(),
            activeTickColor = contentColorFor(MaterialTheme.colors.primary)
                .copy(alpha = SliderDefaults.TickAlpha),
        )
    )
}


@Composable
fun RoastForm(
    @StringRes text: Int,
    modifier: Modifier = Modifier,
    levelOfRoast: Float,

    onValueChange:(Float)-> Unit
//    scaffoldState: ScaffoldState,
//    coroutineScope: CoroutineScope,
//    context: Context,
//    textInfo: Int,
) {

//    fun onClickInfo(textInfo: Int) {
//        coroutineScope.launch {
//            scaffoldState.snackbarHostState.showSnackbar(
//                message = context.getString(textInfo),
//                duration = SnackbarDuration.Short
//            )
//        }
//    }

    Surface(
        shape = RoundedCornerShape(bottomEnd = 8.dp, bottomStart = 8.dp),
        modifier = modifier
            .padding(
                start = 6.dp,
                end = 6.dp,
                bottom = 3.dp
            )
    ) {
        Column(
            modifier = modifier
                .padding(
                    start = 8.dp,
                    end = 8.dp,
                    top = 4.dp
                )
                .fillMaxWidth()
        ) {
            Row {
                Text(
                    stringResource(id = text),
                    modifier = modifier
                        .padding(start = 8.dp),
                    fontSize = 15.sp,
                    color = MaterialTheme.colors.onSecondary
                )
                Spacer(modifier = modifier.size(2.dp))
//                InfoIcon(
//                    modifier = modifier.align(Alignment.CenterVertically),
//                    onClickInfo = { onClickInfo(textInfo) })
                Spacer(modifier = modifier.weight(1f, true))
            }
            RoastLevelSlider(
                modifier = modifier
                    .padding(bottom = 4.dp),
                levelOfRoast = levelOfRoast,
                onValueChange = { onValueChange(it) }
            )
        }
    }
}