package com.example.assn5.ui.screens

import android.util.Log
import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.horizontalDrag
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.positionChange
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.assn5.ui.components.Card
import com.example.assn5.ui.viewmodels.HomeViewModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@Composable
fun HomeScreen() {
    val viewModel: HomeViewModel= viewModel()
    val state = viewModel.uiState
    var offsetX by remember {
        mutableStateOf(0f)
    }
    LaunchedEffect(true){
        viewModel.setUpInitialState(0)
    }
    viewModel.getFlashcards()
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        val rotation = remember{ Animatable(0f) }
        Row (modifier = Modifier
            .fillMaxSize()
            .offset(offsetX.dp, 0.dp)
            .graphicsLayer {
                rotationY = rotation.value
            }
            .pointerInput(Unit) {

                coroutineScope {
                    while (true) {
                        val pointerId = awaitPointerEventScope {
                            awaitFirstDown().id
                        }
                        rotation.stop()
                        awaitPointerEventScope {
                            horizontalDrag(pointerId) {
                                launch {
                                    if (!state.flipped) {
                                        if(it.positionChange().x > 0 && !state.dragged){
                                            offsetX+=it.positionChange().x
                                        }else {
                                            state.dragged = true
                                            var rot =
                                                clamp(rotation.value + it.positionChange().x / 5f)
                                            rotation.snapTo(
                                                rot
                                            )
                                            if (rot < -88f) {
                                                state.display = state.back
                                                state.flipped = true
                                            }
                                        }
                                    } else if(state.flipped){
                                        if(it.positionChange().x < 0 && !state.dragged) {
                                            offsetX+=it.positionChange().x
                                        }
                                        else {
                                            state.dragged = true
                                            var rot =
                                                clamp(rotation.value + it.positionChange().x / 5f)
                                            rotation.snapTo(
                                                rot
                                            )
                                            if (rot > 88f) {
                                                state.display = state.front
                                                state.flipped = false
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        launch {
                            rotation.animateTo(0f)
                            state.dragged = false
                        }
                    }
                }
            },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround){
            Card(prompt = state.display)
        }
    }
}
fun clamp(value:Float, min: Float=-89f, max:Float = 89f): Float{
    if(value >= max) return max
    if(value<= min) return min
    return value
}