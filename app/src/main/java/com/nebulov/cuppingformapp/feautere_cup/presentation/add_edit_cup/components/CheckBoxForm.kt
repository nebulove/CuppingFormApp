package com.nebulov.cuppingformapp.feautere_cup.presentation.add_edit_cup.components

import androidx.annotation.StringRes
import androidx.compose.animation.*
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun CheckBoxForm(
    modifier: Modifier = Modifier,
    @StringRes text: Int,
//    textDescriptors: MutableState<String>,
    checkboxValue: Int,
    cup1: Boolean,
    cup2: Boolean,
    cup3: Boolean,
    cup4: Boolean,
    cup5: Boolean,
    onCheckedChange1: (Boolean) -> Unit,
    onCheckedChange2: (Boolean) -> Unit,
    onCheckedChange3: (Boolean) -> Unit,
    onCheckedChange4: (Boolean) -> Unit,
    onCheckedChange5: (Boolean) -> Unit,
//    scaffoldState: ScaffoldState,
//    coroutineScope: CoroutineScope,
//    context: Context,
//    textInfo: Int,
) {

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
            AnimatedValue(checkboxValue)

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
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = modifier.fillMaxWidth()
            ) {
                OneCheckBox(cup = cup1, onCheckedChange = { onCheckedChange1(it) })
                OneCheckBox(cup = cup2, onCheckedChange = { onCheckedChange2(it) })
                OneCheckBox(cup = cup3, onCheckedChange = { onCheckedChange3(it) })
                OneCheckBox(cup = cup4, onCheckedChange = { onCheckedChange4(it) })
                OneCheckBox(cup = cup5, onCheckedChange = { onCheckedChange5(it) })
            }
//            NotesForm(modifier, textDescriptors)
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedValue(count: Int) {
    AnimatedContent(
        targetState = count,
        transitionSpec = {
            if (targetState > initialState) {
                slideInVertically { height -> height } + fadeIn() with
                        slideOutVertically { height -> -height } + fadeOut()
            } else {
                slideInVertically { height -> -height } + fadeIn() with
                        slideOutVertically { height -> height } + fadeOut()
            }.using(
                SizeTransform(clip = false)
            )
        }
    ) { targetCount ->
        Text(
            text = targetCount.toString(),
            fontSize = 18.sp,
        )
    }
}

@Composable
fun OneCheckBox(
    cup: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {


//    fun changed(checked: Boolean,) {
//        if (!checked) {
//            cup = false
//            checkboxValue += 2
//        } else {
//            cup = true
//            checkboxValue -= 2
//        }
//    }

    Checkbox(
        checked = cup,
        onCheckedChange = { onCheckedChange(it) }
    )


}
