@file:OptIn(ExperimentalAnimationApi::class, ExperimentalAnimationApi::class)

package com.nebulov.cuppingformapp.feautere_cup.presentation.cups.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.nebulov.cuppingformapp.R

@Composable
fun AnimationImage(
    modifier: Modifier = Modifier,
    shown: State<Boolean>
) {

    Column(
        modifier = modifier
//            .wrapContentSize()
//            .fillMaxSize()
        ,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Box(
            modifier = modifier.offset(y = 20.dp),
            contentAlignment = Alignment.Center,
        ) {
            AnimationImage02(shown = shown)
            AnimationImage01(shown = shown)
            AnimationImage03(shown = shown)

        }
        WallpaperEmptyList(shown = shown)
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimationImage01(
    modifier: Modifier = Modifier,
    shown: State<Boolean>
) {
    AnimatedVisibility(
        visible = shown.value,
        exit =
        shrinkVertically(
            animationSpec = tween(1500)
        ),
        enter =
        slideInVertically(
            animationSpec = tween(1000)
        ),
    )
    {
        Image(
            painter = if (isSystemInDarkTheme()) {
                painterResource(R.drawable.image01_night)
            } else painterResource(
                R.drawable.image01_day
            ),
            contentDescription = "",
            modifier = modifier
                .fillMaxHeight(0.6f),
        )
    }
}

@Composable
fun AnimationImage02(
    modifier: Modifier = Modifier,
    shown: State<Boolean>
) {
    AnimatedVisibility(
        visible = shown.value,
        exit =
        shrinkVertically(
            animationSpec = tween(1500)
        ),
        enter = slideInVertically(
            animationSpec = tween(1000),
            initialOffsetY = { it / 2 }
        ),
    )
    {
        Image(
            painter = painterResource(R.drawable.image02_day),
            contentDescription = "",
            modifier = modifier
                .fillMaxHeight(0.6f),
        )
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimationImage03(
    modifier: Modifier = Modifier,
    shown: State<Boolean>
) {
    val transition = rememberInfiniteTransition()
    val sizeRatio = transition.animateFloat(
        initialValue = 0.5f,
        targetValue = 0.6f,
        animationSpec = infiniteRepeatable(
            animation = tween(3000),
            repeatMode = RepeatMode.Reverse
        )
    )

    AnimatedVisibility(
        visible = shown.value,
        exit =
        shrinkVertically(
            animationSpec = tween(1500)
        ),
        enter = scaleIn(
            animationSpec = tween(
                2000
            )
        ),
    )
    {
        Image(
            painter = painterResource(R.drawable.image03_day),
            contentDescription = "",
            modifier = modifier
                .fillMaxHeight(sizeRatio.value),
        )
    }
}