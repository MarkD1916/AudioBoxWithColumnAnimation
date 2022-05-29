package com.example.audioboxwithcolumnanimation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.audioboxwithcolumnanimation.ui.theme.AudioBoxWithColumnAnimationTheme
import com.ill.jp.assignments.screens.questions.component.PlayAudioBox
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AudioBoxWithColumnAnimationTheme {
                Box(modifier = Modifier.fillMaxSize()) {
                    val randomValues = List(2) {
                        Random.nextInt(
                            5,
                            85
                        )
                    }
                    Column(modifier = Modifier.align(Alignment.Center)) {
                        PlayAudioBox(randomValues)
                    }

                }
            }
        }
    }
}
