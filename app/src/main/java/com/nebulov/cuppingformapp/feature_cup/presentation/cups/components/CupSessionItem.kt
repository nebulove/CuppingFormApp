package com.nebulov.cuppingformapp.feature_cup.presentation.cups.components


import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.nebulov.cuppingformapp.feature_cup.domain.model.Cup


@Composable
fun CupSessionItem(
    cup: Cup,
    modifier: Modifier = Modifier,
    top: Dp = 8.dp,
    bottom: Dp = 3.dp,
    roundedCornerShape: RoundedCornerShape = RoundedCornerShape(8.dp)
) {

    val scrollState = rememberScrollState()

    Surface(
        shape = roundedCornerShape,
        modifier = modifier
            .padding(
                top = top,
                start = 10.dp,
                end = 10.dp,
                bottom = bottom
            )
    )
    {
        Column {
            Surface(color = MaterialTheme.colors.primary,
                modifier = modifier
                    .height(1.dp)) {
                Spacer(modifier.fillMaxSize())
            }
            Row(
                modifier = modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Spacer(modifier = modifier.width(6.dp))
                Text(
                    maxLines = 1,
                    modifier = modifier
                        .fillMaxWidth(0.7f)
                        .horizontalScroll(scrollState)
                        .padding(
                            top = 9.dp,
                            bottom = 9.dp
                        ),
                    text = cup.name,
                )
                Spacer(modifier = modifier.weight(1f, true))
                Spacer(modifier = modifier.width(1.dp))
                Text(
                    modifier = modifier
                        .fillMaxWidth(0.6f)
                        .padding(
                            top = 9.dp,
                            bottom = 10.dp
                        ),
                    text = cup.finalScore.toString(),
                    fontWeight = FontWeight.W600,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = modifier.weight(1f, true))
            }
        }

    }
}

