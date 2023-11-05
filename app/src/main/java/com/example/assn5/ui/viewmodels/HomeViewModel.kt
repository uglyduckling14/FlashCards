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
    var flashcards = mutableListOf<Flashcard>()
    var front by mutableStateOf("")
    var back by mutableStateOf("")
    var display by mutableStateOf("")
    var flipped by mutableStateOf(false)
    var dragged by mutableStateOf(false)
}
class HomeViewModel(application: Application): AndroidViewModel(application) {
    val uiState = HomeScreenState()

    fun getFlashcards(){
        val repository = FlashcardRepository()
        uiState.flashcards = mutableListOf()
        if(uiState.index == 0){
            uiState.front=(repository.getOne(uiState.index).front.toString())
            uiState.back = repository.getOne(uiState.index).back.toString()
            uiState.flashcards.add(0,repository.getOne(0))
            uiState.flashcards.add(1, repository.getOne(1))
            return
        }
        val lastIndex = repository.getFlashcards().lastIndex
        if(uiState.index == lastIndex){
            uiState.flashcards.add(lastIndex-1, repository.getOne(lastIndex-1))
            uiState.front=(repository.getOne(uiState.index).front.toString())
            uiState.back = repository.getOne(uiState.index).back.toString()
            uiState.flashcards.add(lastIndex, repository.getOne(lastIndex))
            return
        }
        uiState.flashcards.add( uiState.index-1, repository.getOne(uiState.index-1))
        uiState.flashcards.add( uiState.index, repository.getOne(uiState.index))
        uiState.front=(repository.getOne(uiState.index).front.toString())
        uiState.back = repository.getOne(uiState.index).back.toString()
        uiState.flashcards.add( uiState.index+1, repository.getOne(uiState.index+1))
    }
    fun setUpInitialState(index:Int){
        uiState.index = index
        val repository = FlashcardRepository()
        uiState.front=(repository.getOne(uiState.index).front.toString())
        uiState.back = repository.getOne(uiState.index).back.toString()
        uiState.display = uiState.front
    }
}