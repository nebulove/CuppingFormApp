package com.nebulov.cuppingform.ui.components

import androidx.annotation.StringRes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.nebulov.cuppingformapp.feautere_cup.presentation.add_edit_cup.components.AnimatedValue
import com.nebulov.cuppingformapp.R

@Composable
fun DefectsForm(
    @StringRes text: Int,
    modifier: Modifier = Modifier,
    defectsValue1: Int,
    defectsValue2: Int,
    defectsResult: Int,
    onValueInc1:()-> Unit,
    onValueDec1:()-> Unit,
    onValueInc2:()-> Unit,
    onValueDec2:()-> Unit,
//    textDescriptors: MutableState<String>,
//    scaffoldState: ScaffoldState,
//    coroutineScope: CoroutineScope,
//    context: Context,
//    textInfo: Int,
) {

//    defectsResult = 0 - (defectsValue1 * 2 + defectsValue2 * 4)

//    fun onClickInfo(textInfo: Int) {
//        coroutineScope.launch {
//            scaffoldState.snackbarHostState.showSnackbar(
//                message = context.getString(textInfo),
//                duration = SnackbarDuration.Short
//            )
//        }
//    }

    Surface(
        shape = RoundedCornerShape(8.dp),
        modifier = modifier
            .padding(
                top = 3.dp,
                start = 6.dp,
                end = 6.dp,
                bottom = 3.dp
            )
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioNoBouncy,
                    stiffness = Spring.StiffnessMedium
                )
            )
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = modifier
                .padding(
                    start = 16.dp,
                    end = 11.dp,
                    top = 4.dp
                )
                .fillMaxWidth()
        ) {
            Text(
                stringResource(id = text)
            )
            Spacer(modifier = modifier.size(2.dp))
//            InfoIcon(
//                modifier = modifier.align(Alignment.CenterVertically),
//                onClickInfo = { onClickInfo(textInfo) })
            Spacer(modifier = modifier.weight(1f, true))
            AnimatedValue(defectsResult)
        }
        Column(
            modifier = modifier
                .padding(
                    start = 8.dp,
                    end = 8.dp,
                    top = 26.dp,
                    bottom = 4.dp
                )
        ) {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Defects(
                    text = R.string.Taint,
                    modifier,
                    defectsValue = defectsValue1,
                    onValueDec = { onValueDec1() },
                    onValueInc = { onValueInc1() },
                )
                Defects(
                    text = R.string.Fault, modifier,
                    defectsValue = defectsValue2,
                    onValueDec = { onValueDec2() },
                    onValueInc = { onValueInc2() },
                )
            }
//            NotesForm(modifier, textDescriptors)
        }
    }
}
