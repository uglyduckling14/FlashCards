package com.example.assn5.ui.viewmodels

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import com.example.assn5.ui.models.Flashcard
import com.example.assn5.ui.repositories.FlashcardRepository

class HomeScreenState{
    var index by mutableStateOf(0)
    var flashcards = FlashcardRepository().getFlashcards()
    var front by mutableStateOf("")
    var back by mutableStateOf("")
    var display by mutableStateOf("")
    var flipped by mutableStateOf(false)
    var dragged by mutableStateOf(false)
}
class HomeViewModel(application: Application): AndroidViewModel(application) {
    val uiState = HomeScreenState()

    fun setUpInitialState(index:Int){
        uiState.index = index
        val repository = FlashcardRepository()
        uiState.front=(repository.getOne(uiState.index).front.toString())
        uiState.back = repository.getOne(uiState.index).back.toString()
        uiState.display = uiState.front
        uiState.flashcards = repository.getFlashcards()
    }
}