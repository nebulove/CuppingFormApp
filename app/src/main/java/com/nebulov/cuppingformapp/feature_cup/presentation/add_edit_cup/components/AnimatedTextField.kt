package com.nebulov.cuppingformapp.feature_cup.presentation.add_edit_cup.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.nebulov.cuppingformapp.R

@Composable
fun AnimatedTextField(
    modifier: Modifier = Modifier, shown: State<Boolean>,
    sampleName: State<String>,
    onTextEdit: (String)-> Unit,
    showOff: () -> Unit,
) {


    AnimatedVisibility(
        visible = shown.value,
        enter = slideInVertically(
            // Enters by sliding in from offset -fullHeight to 0.
            initialOffsetY = { fullHeight -> -fullHeight },
            animationSpec = tween(durationMillis = 350, easing = LinearOutSlowInEasing)
        ),
        exit = slideOutVertically(
            // Exits by sliding out from offset 0 to -fullHeight.
            targetOffsetY = { fullHeight -> -fullHeight },
            animationSpec = tween(durationMillis = 550, easing = FastOutLinearInEasing)
        )
    ) {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            elevation = 0.dp,
            shape = RoundedCornerShape(bottomEnd = 8.dp, bottomStart = 8.dp)
        ) {
            TextField(
                modifier = modifier,
                shape = RoundedCornerShape(8.dp),
                value = sampleName.value,
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = MaterialTheme.colors.primary.copy(alpha = 0.1f)
                ),
                singleLine = true,
                maxLines = 1,
                onValueChange = { onTextEdit(it) },
                trailingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.send_48),
                        contentDescription = "Save",
                        tint = MaterialTheme.colors.primary,
                        modifier = modifier
                            .clickable(onClick = showOff)
                            .size(25.dp)
                    )
                }
            )
        }
    }
}