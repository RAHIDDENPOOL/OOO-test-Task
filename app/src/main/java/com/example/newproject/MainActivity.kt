package com.example.newproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Transition
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ContentView()
        }
    }

    @Composable
    private fun ContentView() {
        var content by remember {
            mutableStateOf("Floating Acton Menu")
        }
        // Для анимации выдвижения и скрытия
        var isOpen by remember {
            mutableStateOf(false)
        }

        Box(
            modifier = Modifier.fillMaxSize()
        )
        Image(
            painter = painterResource(id = R.drawable.content),
            contentDescription = stringResource(id = R.string.dog_content_description)
        )
    }

    @Composable
    private fun FloatingActionButtonLayout(isOpen: Boolean) {
        val transition = updateTransition(targetState = isOpen, label = "")
    }

    @Composable
    private fun transitionAnimation(
        transition: Transition<Boolean>,
        valueForTrue: Float,
        valueForFalse: Float
    ): Float {
        val animationValue: Float by transition.animateFloat(
            label = "",
            transitionSpec = {
                tween(durationMillis = 350)
            }
        ) {
            if (it) {
                valueForTrue
            } else {
                valueForFalse
            }
        }
        return animationValue
    }
}




