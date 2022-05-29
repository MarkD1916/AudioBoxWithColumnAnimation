package com.ill.jp.assignments.screens.questions.component

import android.content.res.Resources
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
sealed class AnimationType {
    object InfiniteView : AnimationType()
    object InfiniteViewReverse : AnimationType()
    object FiniteView : AnimationType()
}

sealed class TargetViewPoint {
    object FromStartToEnd : TargetViewPoint()
    data class CustomTargetPoint(
        val start: Float,
        val end: Float
    ) : TargetViewPoint()
}

data class AnimationSettings(
    val animationType: AnimationType,
    val easing: Easing,
    val duration: Int,
    val delay: Int,
    val repeatMode: RepeatMode,
    val targetViewPoint: TargetViewPoint
)

fun pxToDp(px: Float): Float {
    return (px / Resources.getSystem().displayMetrics.density)
}

@Composable
fun ColumnAnimation(
    modifier: Modifier = Modifier.fillMaxSize(),
    columnNumber: Int = 5,
    boxSize: Size = Size(800f, 800f),
    alpha: Float = 1f,
    columnWidth: Float = 7F,
    isFreeze: Boolean = true,
    randomValues: List<Int>
) {

    val infiniteTransition = rememberInfiniteTransition()

    val height = boxSize.height

    val animations = arrayListOf<State<Float>>()


    val settings = makeAnimationForColumn(height, randomValues)



    settings.forEach {

        val initialValue = when (it.targetViewPoint) {
            is TargetViewPoint.FromStartToEnd -> {
                if (it.animationType is AnimationType.InfiniteView)
                    0f else pxToDp(boxSize.width)
            }
            is TargetViewPoint.CustomTargetPoint -> {
                it.targetViewPoint.start
            }
        }

        val targetValue = when (it.targetViewPoint) {
            is TargetViewPoint.FromStartToEnd -> {
                if (it.animationType is AnimationType.InfiniteView)
                    pxToDp(boxSize.width) else 0f
            }
            is TargetViewPoint.CustomTargetPoint -> {
                it.targetViewPoint.end
            }
        }

        animations.add(
            infiniteTransition.animateFloat(
                initialValue = initialValue,
                targetValue = targetValue,
                animationSpec = infiniteRepeatable(
                    animation = tween(
                        it.duration,
                        it.delay,
                        easing = it.easing
                    ),
                    repeatMode = it.repeatMode
                )
            )
        )
    }

    Box(
        modifier = modifier
    ) {

        Canvas(
            modifier = modifier
                .background(Color(0xFFFAFAFA)),
        ) {
            val canvasWidth = size.width
            val canvasHeight = size.height
            var stepSpace = 0f

            for (i in 0 until columnNumber-1) {

                stepSpace += canvasWidth / columnNumber
                drawLine(
                    color = Color(0xFF0076CF),
                    start = Offset(
                        stepSpace,
                        canvasHeight
                    ),
                    end = Offset(
                        stepSpace, if (isFreeze) animations[i].value else height * (randomValues[i].toFloat()/100)),
                    strokeWidth = canvasWidth/columnNumber
                )
            }

        }

    }


}


fun makeAnimationForColumn(
    height: Float,
    randomValue: List<Int>
): ArrayList<AnimationSettings> {
    val settings = arrayListOf<AnimationSettings>()

    for (i in 0 until randomValue.size) {
        val setting = AnimationSettings(
            animationType = AnimationType.InfiniteView,
            easing = LinearEasing,
            duration =700 + (300 * (randomValue[i].toFloat() / 100)).toInt(),
            delay = 0,
            repeatMode = RepeatMode.Reverse,
            targetViewPoint = TargetViewPoint.CustomTargetPoint(
                start = height,
                end = height * (randomValue[i].toFloat() / 100)
            )
        )


        settings.add(setting)
    }
    return settings
}