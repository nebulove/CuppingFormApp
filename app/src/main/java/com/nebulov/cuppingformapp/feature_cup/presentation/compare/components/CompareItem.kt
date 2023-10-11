package com.nebulov.cuppingformapp.feature_cup.presentation.compare.components

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nebulov.cuppingformapp.feature_cup.domain.model.Cup
import com.nebulov.cuppingformapp.feature_cup.presentation.compare.VerticalStripe

@Composable
fun CompareItem(
    cup: Cup,
    modifier: Modifier = Modifier,
    scrollState: ScrollState
) {

    Column(
        modifier = modifier
            .verticalScroll(scrollState)
            .width(80.dp)
            .padding(
                top = 3.dp,
                bottom = 3.dp
            )
    ) {
        Text(text = cup.name, maxLines = 1, modifier = modifier)
        VerticalStripe()
        Text(text = cup.levelOfRoast.toString())
        VerticalStripe()
        Text(text = cup.fragrance.toString())
        Text(text = cup.dry.toString())
        Text(text = cup.breakAroma.toString())
        Text(text = cup.flavor.toString())
        Text(text = cup.aftertaste.toString())
        Text(text = cup.acidity.toString())
        Text(text = cup.intensity.toString())
        Text(text = cup.body.toString())
        Text(text = cup.levelOfBody.toString())
        Text(text = cup.balance.toString())
        Text(text = cup.uniformity.toString())
        Text(text = cup.cleanCup.toString())
        Text(text = cup.sweetness.toString())
        Text(text = cup.defects.toString())
        Text(text = cup.overall.toString())
        Text(text = cup.finalScore.toString())
    }

}