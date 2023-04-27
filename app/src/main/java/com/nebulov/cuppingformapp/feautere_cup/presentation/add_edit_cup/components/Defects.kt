package com.nebulov.cuppingform.ui.components

import androidx.annotation.StringRes
import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun Defects(
    @StringRes text: Int,
    modifier: Modifier = Modifier,
    defectsValue: Int,
    onValueInc:()-> Unit,
    onValueDec:()-> Unit,
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .padding(0.dp)
        ) {

            IconButton(
                onClick = { onValueDec() },
                modifier = Modifier.size(15.dp),
                enabled = defectsValue > 0
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Remove"
                )
            }
            AnimatedDefectsValue(defectsValue)
            IconButton(
                onClick = { onValueInc() },
                modifier = Modifier.size(15.dp),
                enabled = defectsValue < 5
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowForward,
                    contentDescription = "Add"
                )
            }
        }
        Text(
            stringResource(id = text),
            fontSize = 16.sp
        )
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedDefectsValue(count: Int, modifier: Modifier = Modifier) {
    AnimatedContent(
        targetState = count,
        transitionSpec = {
            if (targetState > initialState) {
                slideInVertically { height -> height } + fadeIn() with
                        slideOutVertically { height -> -height } + fadeOut()
            } else {
                slideInVertically { height -> -height } + fadeIn() with
                        slideOutVertically { height -> height } + fadeOut()
            }.using(
                SizeTransform(clip = false)
            )
        }
    ) { targetCount ->
        Text(
            text = targetCount.toString(),
            fontSize = 18.sp,
            modifier = modifier.padding(start = 8.dp, end = 8.dp)
        )
    }
}



