package com.nebulov.cuppingformapp.feautere_cup.presentation.cups.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nebulov.cuppingformapp.R

@Composable
fun WallpaperEmptyList(
    modifier: Modifier = Modifier,
    shown: Boolean
) {
    AnimatedVisibility(
        visible = shown,
        exit =
        fadeOut(animationSpec = tween(800)) +
                shrinkVertically(
                    animationSpec = tween(
                        1400,
                    )
                ),
        enter =
        fadeIn(animationSpec = tween(800)) +
                expandVertically(
                    animationSpec = tween(
                        1400,
                    )
                ),
    )
    {
        Column(
            modifier = modifier
                .wrapContentSize()
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            val color: Color = MaterialTheme.colors.onPrimary.copy(alpha = 0.9f)

            Image(
                painter = if (isSystemInDarkTheme()) {
                    painterResource(R.drawable.landing_screen_night)
                } else painterResource(
                    R.drawable.landing_screen_day
                ),
                contentDescription = "",
                modifier = modifier
                    .fillMaxHeight(0.6f)
                    .offset(y = 20.dp),
                alpha = 1f
            )
            Text(text = "Let's Go!", color = color, fontSize = 28.sp, fontWeight = FontWeight.W900)
            Spacer(modifier = modifier.height(4.dp))
            Text(
                text = "Click on the button to start cupping",
                color = color,
                fontSize = 18.sp,
                fontWeight = FontWeight.W200
            )
        }
    }
}