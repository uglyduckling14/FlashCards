package com.example.assn5.ui.models

data class Flashcard(
    val front: String? = null,
    val back: String? = null,
    val flipped: Boolean,
    val beingDragged: Boolean
){

}