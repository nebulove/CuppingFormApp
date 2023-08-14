package com.nebulov.cuppingformapp.feature_cup.presentation.cups.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nebulov.cuppingformapp.R

@Composable
fun CupSessionTitleItem(
    modifier: Modifier = Modifier,
    date: String,
    onDeleteClick: () -> Unit,
    shown: MutableState<Boolean> = remember { mutableStateOf(false) }
) {

    val primaryColorBrush = Brush.verticalGradient(
        colors = listOf(
            MaterialTheme.colors.primary,
            MaterialTheme.colors.primary
        )
    )

    Surface(
        shape = RoundedCornerShape(
            bottomEnd = 0.dp,
            bottomStart = 0.dp,
            topEnd = 8.dp,
            topStart = 8.dp
        ),
        modifier = modifier
            .wrapContentWidth()
            .padding(
                top = 8.dp,
                start = 10.dp,
                end = 10.dp,
            )
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 300,
                    delayMillis = 50,
                    easing = LinearOutSlowInEasing
                )
            )
    )
    {
        Row(
            modifier = modifier.wrapContentWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = date,
                fontSize = 12.sp,
                modifier = modifier
                    .padding(start = 15.dp, top = 0.dp, end = 0.dp),
                color = MaterialTheme.colors.primary,
                fontWeight = FontWeight.W600

            )
            if (!shown.value) {
                DeleteIcon(
                    modifier = modifier.clickable(onClick = { shown.value = !shown.value }),
                    R.drawable.delete48,
                    tint = MaterialTheme.colors.primary,
                    text = R.string.Delete,
                    start = 10.dp,
                    end = 10.dp,
                    brushGradient = primaryColorBrush
                )
            }
            if (shown.value) {
                Column(modifier = modifier) {
                    DeleteIcon(
                        modifier = modifier.clickable(onClick = { shown.value = !shown.value }),
                        R.drawable.outline_close_24,
                        tint = MaterialTheme.colors.primary,
                        text = R.string.Delete,
                        start = 10.dp,
                        end = 10.dp,
                        brushGradient = primaryColorBrush
                    )
//                    Text(text = "CLOSE", fontSize = 9.sp)

                }
                Column() {
                    DeleteIcon(
                        modifier = modifier.clickable(onClick = {
                            onDeleteClick()
                            shown.value = false
                        }),
                        R.drawable.outline_done_24,
                        tint = MaterialTheme.colors.primary,
                        text = R.string.Delete,
                        start = 10.dp,
                        end = 10.dp,
                        brushGradient = primaryColorBrush
                    )
//                    Text(text = "DELETE", fontSize = 9.sp)
                }
            }
        }
    }
}
