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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
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
        // Floating Action Men
        FloatingActionButtonLayout(
            isOpen = isOpen,
            onToggle = { isOpen = !isOpen },
            onClose = { state, selectMenu ->
                if (state) {
                    isOpen = !state
                }
                if (selectMenu != "") {
                    content = selectMenu
                }
            })
    }

    @Composable
    private fun FloatingActionButtonLayout(
        isOpen: Boolean,
        onToggle: () -> Unit,
        onClose: (state: Boolean, selectMenu: String) -> Unit
    ) {
        val transition = updateTransition(targetState = isOpen, label = "")

        //анимация поворота плавающей кнопк
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
                    if (isOpen) {
                        // Отображение меню при клике
                        FloatingActionMenus(
                            isOpen = isOpen,
                            actionMenuScale = actionMenuScale,
                            onClose = onClose
                        )
                        Spacer(modifier = Modifier.padding(vertical = 20.dp))
                    }
                    FloatingActionButton(
                        onClick = {
                            //Отображение или скрытие Меню
                            onToggle()
                        },
                        shape = CircleShape,
                        backgroundColor = Color.White,
                        contentColor = Color.DarkGray,
                        elevation = FloatingActionButtonDefaults.elevation(),
                        content = {
                            Icon(
                                Icons.Filled.Add, contentDescription = null,
                                modifier = Modifier.rotate(rotation)
                            )
                        }
                    )
                    Spacer(modifier = Modifier.padding(vertical = 10.dp))
                }
            }
        }
    }

    @Composable
    private fun FloatingActionMenus(
        isOpen: Boolean,
        actionMenuScale: Float,
        onClose: (state: Boolean, selectedMenu: String) -> Unit
    ) {
        // Column Scope
        {}
        Spacer(modifier = Modifier.padding(vertical = 10.dp))

        Surface(
            shape = CircleShape,
            elevation = 6.dp,
            modifier = Modifier
                .wrapContentSize()
                .scale(actionMenuScale)
        ) {
            Image(
                painter = painterResource(
                    id = R.drawable.ic_baseline_bug_report_24
                ),
                contentScale = ContentScale.Crop,
                contentDescription = null,
                modifier = Modifier
                    .size(46.dp)
                    .clip(CircleShape)
                    .clickable(
                        // custom ripple
                        interactionSource = MutableInteractionSource(),
                        indication = rememberRipple(bounded = true, color = Color.DarkGray),
                        onClick = {
                        }
                    )
            )

        }
        Spacer(modifier = Modifier.padding(vertical = 10.dp))
        Surface(
            shape = CircleShape, modifier = Modifier
                .wrapContentSize()
                .scale(actionMenuScale)
        ) {

            Image(
                painter = painterResource(
                    id = R.drawable.ic_baseline_camera_24
                ),
                contentScale = ContentScale.Crop,
                contentDescription = null,
                modifier = Modifier
                    .size(46.dp)
                    .clip(CircleShape)
                    .clickable(
                        // custom ripple
                        interactionSource = MutableInteractionSource(),
                        indication = rememberRipple(bounded = true, color = Color.DarkGray),
                        onClick = {
                        }
                    )
            )
        }
        Spacer(modifier = Modifier.padding(vertical = 10.dp))
        Surface(
            shape = CircleShape, modifier = Modifier
                .wrapContentSize()
                .scale(actionMenuScale)
        ) {

            Image(
                painter = painterResource(
                    id = R.drawable.ic_baseline_coronavirus_24
                ),
                contentScale = ContentScale.Crop,
                contentDescription = null,
                modifier = Modifier
                    .size(46.dp)
                    .clip(CircleShape)
                    .clickable(
                        // custom ripple
                        interactionSource = MutableInteractionSource(),
                        indication = rememberRipple(bounded = true, color = Color.DarkGray),
                        onClick = {
                        }
                    )
            )
        }
        Spacer(modifier = Modifier.padding(vertical = 10.dp))
        Surface(
            shape = CircleShape, modifier = Modifier
                .wrapContentSize()
                .scale(actionMenuScale)
        ) {

            Image(
                painter = painterResource(
                    id = R.drawable.ic_baseline_extension_24
                ),
                contentScale = ContentScale.Crop,
                contentDescription = null,
                modifier = Modifier
                    .size(46.dp)
                    .clip(CircleShape)
                    .clickable(
                        // custom ripple
                        interactionSource = MutableInteractionSource(),
                        indication = rememberRipple(bounded = true, color = Color.DarkGray),
                        onClick = {
                        }
                    )
            )
        }
        Spacer(modifier = Modifier.padding(vertical = 10.dp))
        Surface(
            shape = CircleShape, modifier = Modifier
                .wrapContentSize()
                .scale(actionMenuScale)
        ) {

            Image(
                painter = painterResource(
                    id = R.drawable.ic_baseline_fingerprint_24
                ),
                contentScale = ContentScale.Crop,
                contentDescription = null,
                modifier = Modifier
                    .size(46.dp)
                    .clip(CircleShape)
                    .clickable(
                        // custom ripple
                        interactionSource = MutableInteractionSource(),
                        indication = rememberRipple(bounded = true, color = Color.DarkGray),
                        onClick = {
                        }
                    )
            )
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





