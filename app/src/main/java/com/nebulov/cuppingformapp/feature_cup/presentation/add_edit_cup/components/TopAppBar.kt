package com.nebulov.cuppingformapp.feature_cup.presentation.add_edit_cup.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nebulov.cuppingformapp.core.Constants.Companion.EMPTY_STRING
import com.nebulov.cuppingformapp.R

@Composable
fun TopAppBarCuppingForm(
    modifier: Modifier = Modifier,
    name: State<String>,
    finalScore: State<Float>,
    showOff: () -> Unit
) {

    var isVisible by rememberSaveable { mutableStateOf(true) }


    TopAppBar(
        modifier = modifier,
        elevation = 0.dp,
        backgroundColor = MaterialTheme.colors.primary
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(end = 3.dp, bottom = 6.dp)
                .animateContentSize(
                    animationSpec = tween(
                        durationMillis = 300,
                        delayMillis = 50,
                        easing = LinearOutSlowInEasing
                    )
                ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {
            Column(
                modifier = modifier
                    .fillMaxWidth(0.7f)
                    .horizontalScroll(rememberScrollState())
            ) {
                if (name.value == EMPTY_STRING) {
                    Text(
                        text = stringResource(R.string.sample),
                        fontSize = 20.sp,
                        modifier = modifier
                            .padding(
                                start = 16.dp,
                            )
                            .clickable(onClick = showOff)
                    )
                }
                if (name.value != EMPTY_STRING) {
                    Text(
                        text = name.value,
                        fontSize = 20.sp,
                        maxLines = 1,
                        softWrap = false,
                        fontWeight = FontWeight.W500,
                        modifier = modifier
                            .padding(
                                start = 16.dp,
                            )
                            .clickable(onClick = showOff)
                    )
                }
            }
            Spacer(modifier = modifier.weight(1f, true))

            Surface(
//                shape = RoundedCornerShape(8.dp),
                color = MaterialTheme.colors.primary,
                modifier = modifier.clip(RoundedCornerShape(8.dp))
                    .width(69.dp)
                    .clickable(onClick = { isVisible = !isVisible })
            ) {
                Column(
                    horizontalAlignment = Alignment.End,
                    modifier = modifier
                        .padding(end = 6.dp)
                ) {
                    Text(
                        text = stringResource(R.string.final_score),
                        fontSize = 9.sp,
                        modifier = modifier.offset(y = 4.dp),
                        fontWeight = FontWeight.W700
                    )
                    Row(
                        horizontalArrangement = Arrangement.End,
                        modifier = modifier.fillMaxWidth()
                    ) {

                        Text(
                            text = if (isVisible)
                                String.format("%.2f", finalScore.value) else EMPTY_STRING,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.W400,
                            overflow = TextOverflow.Visible,
                            softWrap = false
                        )
                    }
                }
                if (!isVisible)
                    Icon(
                        modifier = modifier.offset(y = 18.dp),
                        painter = painterResource(
                            R.drawable.baseline_visibility_off_black_20
                        ),
                        contentDescription = stringResource(R.string.visibility),
                    )
            }
        }
    }
}
