package com.example.assn5.ui.screens

import android.util.Log
import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.horizontalDrag
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.positionChange
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
                                    var rot = clamp(rotation.value + it.positionChange().x / 5f)
                                    rotation.snapTo(
                                        rot
                                    )
                                    if (rot > 88f || rot < -88) { //TODO: Change this so that the card moves when swiped left again and turns back over on swipe right
                                                                    //TODO: Change this so that the card drags when swiped right twice
                                        state.flipped = !state.flipped
                                        if(state.flipped){
                                            state.display = state.back
                                        }else{
                                            state.display = state.front
                                        }
                                    }
                                }
                            }
                        }
                        launch {
                            rotation.animateTo(0f)
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