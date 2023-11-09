package com.example.assn5.ui.screens

import android.util.Log
import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.horizontalDrag
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.input.pointer.positionChanged
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.assn5.ui.components.Card
import com.example.assn5.ui.viewmodels.HomeViewModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

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
    //viewModel.getFlashcards()
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        val rotation = remember{ Animatable(0f) }
        var offset = remember {
            Animatable(100f)
        }
        Row (modifier = Modifier
            .offset { IntOffset(offset.value.roundToInt(), 100) }
            .fillMaxSize()
            .pointerInput(Unit) {
                coroutineScope {
                    while (true) {
                        val pointerId = awaitPointerEventScope {
                            awaitFirstDown().id
                        }
                        rotation.stop()
                        offset.stop()
                        awaitPointerEventScope {
                            horizontalDrag(pointerId) {
                                if (state.dragged && it.positionChanged()) {
                                    offsetX += it.positionChange().x
                                    launch {
                                        offset.snapTo(
                                            offsetX
                                        )}
                                } else
                                    if (!state.flipped && !state.dragged) {
                                        launch {
                                            val rot =
                                                clamp(rotation.value + it.positionChange().x / 5f)
                                            rotation.snapTo(
                                                rot
                                            )
                                            state.flipped = true
                                        }
                                    } else if (state.flipped && !state.dragged) {
                                        launch {
                                            val rot =
                                                clamp(rotation.value + it.positionChange().x / 5f)
                                            rotation.snapTo(
                                                rot
                                            )
                                            state.flipped = false
                                        }
                                    }
                            }
                            launch {
                                if(state.dragged){
                                    if (offset.value < -50f && state.index != 19) {
                                        viewModel.setUpInitialState(state.index + 1)
                                        offset.animateTo(-50f)
                                        offsetX = 0f
                                        state.dragged = false
                                    }else if(offset.value > 50f && state.index != 0){
                                        viewModel.setUpInitialState(state.index-1)
                                        offset.animateTo(800f)
                                        offsetX = 0f
                                        state.dragged = false
                                    }
                                }else if(state.flipped){
                                    state.dragged = true
                                    state.display = state.back
                                }else if(!state.flipped){
                                    state.display = state.front
                                }
                                rotation.animateTo(0f)

                            }
                        }
                    }
                }
            },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround){
            state.flashcards.forEachIndexed{index, card ->
                if(index == state.index){
                    Box(modifier = Modifier
                        .size(200.dp, 200.dp)
                        .offset { IntOffset(offset.value.roundToInt(), 100)}
                        .graphicsLayer {
                            rotationY = rotation.value
                        }) {
                        Card(prompt = state.display)
                    }
                }
                if(index == state.index - 1 && state.index !=0){
                    Box(modifier = Modifier
                        .size(200.dp, 200.dp)
                        .offset { IntOffset(offset.value.roundToInt() - 1000, 100)}
                        .graphicsLayer {
                            rotationY = rotation.value
                        }) {
                        Card(prompt = state.flashcards[index].front)
                    }
                }
                if(index == state.index + 1 && state.index !=19){
                    Box(modifier = Modifier
                        .size(200.dp, 200.dp)
                        .offset { IntOffset(offset.value.roundToInt() + 1000, 100)}
                        .graphicsLayer {
                            rotationY = rotation.value
                        }) {
                        Card(prompt = state.flashcards[index].front)
                    }
                }
            }
        }
    }
}
fun clamp(value:Float, min: Float=-89f, max:Float = 89f): Float{
    if(value >= max) return max
    if(value<= min) return min
    return value
}