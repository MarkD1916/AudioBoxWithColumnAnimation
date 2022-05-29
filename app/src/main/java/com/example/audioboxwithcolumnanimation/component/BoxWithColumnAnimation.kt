package com.ill.jp.assignments.screens.questions.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.toSize


@Composable
fun BoxWithColumnAnimation(
    modifier: Modifier,
    isFreeze: Boolean = true,
    randomValues: List<Int>,
    content: @Composable BoxScope.() -> Unit
) {
    var boxSize by remember { mutableStateOf(Size.Zero) }

    Box(
        modifier = modifier
            .onGloballyPositioned { coordinates ->
                boxSize = coordinates.size.toSize()
            }.fillMaxSize()

    ) {
        if(boxSize.height!=0f) {
            ColumnAnimation(
                Modifier.fillMaxSize(),
                columnNumber = randomValues.size,
                boxSize,
                alpha = 1f,
                isFreeze = isFreeze,
                randomValues = randomValues
            )
        }

        content()
    }


}