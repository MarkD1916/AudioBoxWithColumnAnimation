package com.ill.jp.assignments.screens.questions.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.audioboxwithcolumnanimation.R

@Composable
internal fun PlayAudioBox(
    randomValues: List<Int>
) {

    val isPlayingUi = rememberSaveable{ mutableStateOf(false) }

    Box(
        modifier =
        Modifier
            .border(
                width = 1.dp,
                color = Color(0xFF0076CF),
                shape = RoundedCornerShape(12.dp)
            )
            .heightIn(min = 50.dp, max = 50.dp)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 10.dp, end = 10.dp)
        ) {
            Image(
                painter = if (isPlayingUi.value) painterResource(id = R.drawable.ic_baseline_pause_circle_filled_24) else painterResource(
                    id = R.drawable.ic_baseline_play_circle_filled_24
                ),
                contentDescription = "play and pause picture",
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .width(30.dp)
                    .height(30.dp)
                    .clickable {
                        isPlayingUi.value = !isPlayingUi.value
                    }

            )

            Text(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(start = 10.dp),
                text = "Play Audio",
                color = Color.Black,
                fontSize = 12.sp,
            )
        }


        BoxWithColumnAnimation(
            isFreeze = isPlayingUi.value,
            modifier =
            Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 10.dp)
                .sizeIn(
                    minWidth = 70.dp,
                    maxWidth = 70.dp,
                    minHeight = 20.dp,
                    maxHeight = 20.dp,
                ),
            columnNumber = randomValues.size,
            randomValues = randomValues
        ) {

        }
    }
}