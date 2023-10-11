package com.nebulov.cuppingformapp.feature_cup.presentation.compare.components

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
        TextForCompareItem(text = cup.name)
        VerticalStripe()
        TextForCompareItem(text = cup.levelOfRoast.toString())
        VerticalStripe()
        TextForCompareItem(text = cup.fragrance.toString())
        VerticalStripe()
        TextForCompareItem(text = cup.dry.toString())
        VerticalStripe()
        TextForCompareItem(text = cup.breakAroma.toString())
        VerticalStripe()
        TextForCompareItem(text = cup.flavor.toString())
        VerticalStripe()
        TextForCompareItem(text = cup.aftertaste.toString())
        VerticalStripe()
        TextForCompareItem(text = cup.acidity.toString())
        VerticalStripe()
        TextForCompareItem(text = cup.intensity.toString())
        VerticalStripe()
        TextForCompareItem(text = cup.body.toString())
        VerticalStripe()
        TextForCompareItem(text = cup.levelOfBody.toString())
        VerticalStripe()
        TextForCompareItem(text = cup.balance.toString())
        VerticalStripe()
        TextForCompareItem(text = cup.uniformity.toString())
        VerticalStripe()
        TextForCompareItem(text = cup.cleanCup.toString())
        VerticalStripe()
        TextForCompareItem(text = cup.sweetness.toString())
        VerticalStripe()
        TextForCompareItem(text = cup.defects.toString())
        VerticalStripe()
        TextForCompareItem(text = cup.overall.toString())
        VerticalStripe()
        TextForCompareItem(text = cup.finalScore.toString())
    }


}

@Composable
fun TextForCompareItem(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        textAlign = TextAlign.Center,
        fontSize = 16.sp,
        maxLines = 1,
        modifier = modifier
            .heightIn(22.dp)
            .width(80.dp)
    )
}