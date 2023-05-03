package com.nebulov.cuppingformapp.feautere_cup.presentation.cups

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight.Companion.W200
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nebulov.cuppingformapp.R
import kotlinx.coroutines.delay

@Composable
fun LandingScreen(
    onTimeout: () -> Unit,
    modifier: Modifier = Modifier,
) {

    val currentOnTimeout = rememberUpdatedState(onTimeout)


    val scale = remember {
        Animatable(0f)
    }
    val scaleText = remember {
        Animatable(0f)
    }


    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 800,
                easing = {
                    OvershootInterpolator(2f).getInterpolation(it)
                }
            )
        )
        delay(100L)
        scaleText.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 800,
                easing = {
                    OvershootInterpolator(2f).getInterpolation(it)
                }
            )
        )
        delay(400L)
        currentOnTimeout.value()
    }

    Surface(
        modifier = modifier
            .fillMaxSize()
            .wrapContentSize(),
        color = MaterialTheme.colors.primary
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .wrapContentSize(),
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                modifier = modifier
                    .fillMaxWidth(0.4f)
                    .align(CenterHorizontally)
                    .padding(start = 5.dp)
                    .scale(scale.value),
                painter = painterResource(id = R.drawable.logo_2),
                contentDescription = "logo"
            )
            Spacer(modifier = modifier.height(15.dp))
            Text(
                modifier = modifier
                    .align(CenterHorizontally)
                    .scale(scaleText.value),
                text = "Start with a drop",
                fontWeight = W200,
                fontSize = 26.sp
            )
        }
    }
}
