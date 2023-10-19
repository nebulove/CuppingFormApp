package com.nebulov.cuppingformapp.feature_cup.presentation.compare.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nebulov.cuppingformapp.feature_cup.domain.model.Cup
import com.nebulov.cuppingformapp.feature_cup.presentation.compare.HorizonStripe

@Composable
fun CompareItem(
    cup: Cup,
    modifier: Modifier = Modifier,
    scrollState: ScrollState,
    visibilityRL: MutableState<Boolean>,
    visibilityFR: MutableState<Boolean>,
    visibilityDry: MutableState<Boolean>,
    visibilityBreak: MutableState<Boolean>,
    visibilityFlavor: MutableState<Boolean>,
    visibilityAc: MutableState<Boolean>,
    visibilityAf: MutableState<Boolean>,
    visibilityInt: MutableState<Boolean>,
    visibilityBody: MutableState<Boolean>,
    visibilityLvl: MutableState<Boolean>,
    visibilityBalance: MutableState<Boolean>,
    visibilityUn: MutableState<Boolean>,
    visibilityCC: MutableState<Boolean>,
    visibilitySw: MutableState<Boolean>,
    visibilityDefects: MutableState<Boolean>,
    visibilityOverall: MutableState<Boolean>,
    visibilityFS: MutableState<Boolean>,
    colorRL: MutableState<Boolean>,
    colorFR: MutableState<Boolean>,
    colorDry: MutableState<Boolean>,
    colorBreak: MutableState<Boolean>,
    colorFlavor: MutableState<Boolean>,
    colorAc: MutableState<Boolean>,
    colorAf: MutableState<Boolean>,
    colorInt: MutableState<Boolean>,
    colorBody: MutableState<Boolean>,
    colorLvl: MutableState<Boolean>,
    colorBalance: MutableState<Boolean>,
    colorUn: MutableState<Boolean>,
    colorCC: MutableState<Boolean>,
    colorSw: MutableState<Boolean>,
    colorDefects: MutableState<Boolean>,
    colorOverall: MutableState<Boolean>,
    colorFS: MutableState<Boolean>,
) {

    Column(
        modifier = modifier
            .verticalScroll(scrollState)
            .width(80.dp)
    ) {

        TextForCompareItem(text = cup.name, fontSize = 12.sp)
        HorizonStripe()
        if (visibilityRL.value) {
            TextForCompareItem(
                text = cup.levelOfRoast.toString(),
                state = colorRL.value
            )
            HorizonStripe()
        }
        if (visibilityFR.value) {
            TextForCompareItem(
                text = cup.fragrance.toString(),
                state = colorFR.value
            )
            HorizonStripe()
        }
        if (visibilityDry.value) {
            TextForCompareItem(
                text = cup.dry.toString(),
                state = colorDry.value
            )
            HorizonStripe()
        }
        if (visibilityBreak.value) {
            TextForCompareItem(
                text = cup.breakAroma.toString(),
                state = colorBreak.value
            )
            HorizonStripe()
        }
        if (visibilityFlavor.value) {
            TextForCompareItem(
                text = cup.flavor.toString(),
                state = colorFlavor.value
            )
            HorizonStripe()
        }
        if (visibilityAf.value) {
            TextForCompareItem(
                text = cup.aftertaste.toString(),
                state = colorAf.value
            )
            HorizonStripe()
        }
        if (visibilityAc.value) {
            TextForCompareItem(
                text = cup.acidity.toString(),
                state = colorAc.value
            )
            HorizonStripe()
        }
        if (visibilityInt.value) {
            TextForCompareItem(
                text = cup.intensity.toString(),
                state = colorInt.value
            )
            HorizonStripe()
        }
        if (visibilityBody.value) {
            TextForCompareItem(
                text = cup.body.toString(),
                state = colorBody.value
            )
            HorizonStripe()
        }
        if (visibilityLvl.value) {
            TextForCompareItem(
                text = cup.levelOfBody.toString(),
                state = colorLvl.value
            )
            HorizonStripe()
        }
        if (visibilityBalance.value) {
            TextForCompareItem(
                text = cup.balance.toString(),
                state = colorBalance.value
            )
            HorizonStripe()
        }
        if (visibilityUn.value) {
            TextForCompareItem(
                text = cup.uniformity.toString(),
                state = colorUn.value
            )
            HorizonStripe()
        }
        if (visibilityCC.value) {
            TextForCompareItem(
                text = cup.cleanCup.toString(),
                state = colorCC.value
            )
            HorizonStripe()
        }
        if (visibilitySw.value) {
            TextForCompareItem(
                text = cup.sweetness.toString(),
                state = colorSw.value
            )
            HorizonStripe()
        }
        if (visibilityDefects.value) {
            TextForCompareItem(
                text = cup.defects.toString(),
                state = colorDefects.value
            )
            HorizonStripe()
        }
        if (visibilityOverall.value) {
            TextForCompareItem(
                text = cup.overall.toString(),
                state = colorOverall.value
            )
            HorizonStripe()
        }
        if (visibilityFS.value) {
            TextForCompareItem(
                text = cup.finalScore.toString(),
                state = colorFS.value
            )
        }
    }


}

@Composable
fun TextForCompareItem(
    text: String,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 16.sp,
    visibility: Boolean = true,
    state: Boolean = true
) {

    val darkColorChange =
        if (isSystemInDarkTheme()) MaterialTheme.colors.primary.copy(0.7f)
        else
            MaterialTheme.colors.primary.copy(0.3f)

    val bgColor: Color by animateColorAsState(
        if (state) MaterialTheme.colors.background else darkColorChange,
        animationSpec = tween(1000, easing = LinearEasing)
    )

    if (visibility) {
        Text(
            text = text,
            textAlign = TextAlign.Center,
            fontSize = fontSize,
            maxLines = 1,
            modifier = modifier
                .heightIn(22.dp)
                .width(90.dp)
                .background(bgColor)
        )
    }
}