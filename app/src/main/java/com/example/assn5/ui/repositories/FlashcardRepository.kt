package com.example.assn5.ui.repositories

import com.example.assn5.ui.models.Flashcard

class FlashcardRepository {
    private val flashcards:List<Flashcard> = listOf(
        Flashcard("Who played S'chn T'gai Spock?", "Leonard Nimoy", flipped = false, beingDragged = false),
        Flashcard("Who played James T. Kirk", "William Shatner", flipped = false, beingDragged = false),
        Flashcard("Who played Nyota Uhura?", "Nichelle Nichols", flipped = false, beingDragged = false),
        Flashcard("Who played Leonard \"Bones\" McCoy?", "DeForest Kelley", flipped = false, beingDragged = false),
        Flashcard("Who played Montegomery \"Scotty\" Scott?", "James Doohan", flipped = false, beingDragged = false),
        Flashcard("Who played Hikaru Kato Sulu?", "George Takei", flipped = false, beingDragged = false),
        Flashcard("Who played Pavel Andreievich Chekov?", "Walter Koenig", flipped = false, beingDragged = false),
        Flashcard("Who played Christopher Pike?", "Jeffrey Hunter", flipped = false, beingDragged = false),
        Flashcard("Which character's voice becomes the voice for all computers in Star Trek?", "Christine Chapel", flipped = false, beingDragged = false),
        Flashcard("Who played Christine Chapel?", "Majel Barrett", flipped = false, beingDragged = false),
        Flashcard("What year did Star Trek: The Original Series come out?", "1966", flipped = false, beingDragged = false),
        Flashcard("Who played Khan Noonien Singh in 1967?", "Ricardo Montalbán", flipped = false, beingDragged = false),
        Flashcard("Which country was Star Trek: The Original Series aired first?", "Canada", flipped = false, beingDragged = false),
        Flashcard("What was the name of the ship commanded by Captain Kirk?", "USS Enterprise NCC-1701", flipped = false, beingDragged = false),
        Flashcard("What two races is Spock a mix of?", "Vulcan/Human", flipped = false, beingDragged = false),
        Flashcard("Which character was stuck in a transporter buffer for 75 years?", "Scotty", flipped = false, beingDragged = false),
        Flashcard("What species is Khan?", "Genetically Modified Human", flipped = false, beingDragged = false),
        Flashcard("What is the color of Vulcan blood?", "Green", flipped = false, beingDragged = false),
        Flashcard("Why is Vulcan blood green?", "It is copper based", flipped = false, beingDragged = false),
        Flashcard("How long was the Original Series' crew mission?", "5 years", flipped = false, beingDragged = false),
    )
    fun getFlashcards(): List<Flashcard>{
        return flashcards
    }
    fun getOne(index: Int): Flashcard{
        return flashcards[index]
    }
}