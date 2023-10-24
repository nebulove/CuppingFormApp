package com.nebulov.cuppingformapp.feature_cup.presentation.compare.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nebulov.cuppingformapp.R

@Composable
fun GradientButton(
    gradientColors: List<Color>,
    disabledColors: List<Color> = listOf(
        MaterialTheme.colors.surface,
        MaterialTheme.colors.surface
    ),
    text: String,
    onClick: () -> Unit,
    enabled: MutableState<Boolean>

    ) {

    val onPrimary = MaterialTheme.colors.onPrimary
    val primary = MaterialTheme.colors.primary

    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 3.dp, end = 3.dp),
        onClick = {
            enabled.value = !enabled.value
            onClick()
        },
        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Transparent
        ),
        shape = RoundedCornerShape(8.dp),
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.linearGradient(colors = if (enabled.value) disabledColors else gradientColors),
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(horizontal = 16.dp, vertical = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = if (enabled.value) painterResource(R.drawable.baseline_visibility_off_24) else painterResource(
                        R.drawable.outline_visibility_24
                    ), contentDescription = null,
                    tint = if (enabled.value) primary else onPrimary
                )
                Text(
                    text = text,
                    fontSize = 13.sp,
                    color = if (enabled.value) primary else onPrimary
                )
            }
        }
    }
}