@file:OptIn(ExperimentalAnimationApi::class, ExperimentalAnimationApi::class)

package com.nebulov.hluppr.feature_cup.presentation.cups.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
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
        AnimatedWelcomeText(shown = shown)
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
            animationSpec = tween(350)
        ),
        enter =
        slideInVertically(
            animationSpec = tween(700)
        ),
    )
    {
        Image(
            painter = if (isSystemInDarkTheme()) {
                painterResource(R.drawable.image01_night_v2)
            } else painterResource(
                R.drawable.image01_day_v2
            ),
            contentDescription = null,
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
            animationSpec = tween(350)
        ),
        enter = slideInVertically(
            animationSpec = tween(700),
            initialOffsetY = { it / 2 }
        ),
    )
    {
        Image(
            painter = painterResource(R.drawable.image02_day_v2),
            contentDescription = null,
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

    AnimatedVisibility(
        visible = shown.value,
        exit =
        shrinkVertically(
            animationSpec = tween(350)
        ),
        enter = scaleIn(
            animationSpec = tween(
                700, delayMillis = 300
            )
        ),
    )
    {
        Image(
            painter = painterResource(R.drawable.image03_day_v2),
            contentDescription = null,
            modifier = modifier
                .fillMaxHeight(0.6f),
        )
    }
}