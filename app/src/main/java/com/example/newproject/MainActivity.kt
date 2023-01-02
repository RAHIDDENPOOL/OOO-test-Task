package com.example.newproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Transition
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ContentView()
        }
    }
    @Preview
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
    private fun FloatingActionButtonLayout(
        isOpen: Boolean,
        onClose: (state: Boolean, selectMenu: String) -> Unit
    ) {
        val transition = updateTransition(targetState = isOpen, label = "")

        //анимация поворота плавающей кнопки
        val rotation =
            transitionAnimation(transition = transition, valueForTrue = 45f, valueForFalse = 0f)

        //альфа анимация для плавающей кнопки
        val backgroundAlpha = transitionAnimation(
            transition = transition,
            valueForTrue = 0.5f,
            valueForFalse = 0f
        )
        //размер анимации итемов в меню
        val actionMenuScale = transitionAnimation(
            transition = transition,
            valueForTrue = 1f,
            valueForFalse = 0f
        )

        Surface(
            color = Color.Black.copy(alpha = backgroundAlpha),
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable(
                        indication = null,
                        interactionSource = MutableInteractionSource(),
                        onClick = {
                            onClose(isOpen, "")
                        }
                    )
            ) {
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(bottom = 30.dp, end = 20.dp)
                        .wrapContentSize()
                ) {
                    //TODO
                }
            }
        }
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




