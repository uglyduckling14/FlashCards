package com.example.assn5.ui.repositories

import com.example.assn5.ui.models.Flashcard

class FlashcardRepository {
    private val flashcards:List<Flashcard> = listOf(
        Flashcard("Who played S'chn T'gai Spock?", "Leonard Nimoy"),
        Flashcard("Who played James Tiberius Kirk", "William Shatner"),
        Flashcard("Who played Nyota Uhura?", "Nichelle Nichols"),
        Flashcard("Who played Leonard \"Bones\" McCoy?", "DeForest Kelley"),
        Flashcard("Who played Montegomery \"Scotty\" Scott?", "James Doohan"),
        Flashcard("Who played Hikaru Kato Sulu?", "George Takei"),
        Flashcard("Who played Pavel Andreievich Chekov?", "Walter Koenig"),
        Flashcard("Who played Christopher Pike?", "Jeffrey Hunter"),
        Flashcard("Which character's voice becomes the voice for all computers in Star Trek?", "Christine Chapel"),
        Flashcard("Who played Christine Chapel?", "Majel Barrett"),
        Flashcard("What year did Star Trek: The Original Series come out?", "1966"),
        Flashcard("Who played Khan Noonien Singh in 1967?", "Ricardo Montalbán"),
        Flashcard("Which country was Star Trek: The Original Series aired first?", "Canada"),
        Flashcard("What was the name of the ship commanded by Captain Kirk?", "USS Enterprise NCC-1701"),
        Flashcard("What two races is Spock a mix of?", "Vulcan/Human"),
        Flashcard("Which character was stuck in a transporter buffer for 75 years?", "Scotty"),
        Flashcard("What species is Khan?", "Genetically Modified Human"),
        Flashcard("What is the color of Vulcan blood?", "Green"),
        Flashcard("Why is Vulcan blood green?", "It is copper based"),
        Flashcard("How long was the Original Series' crew mission?", "5 years"),
    )
    fun getFlashcards(): List<Flashcard>{
        return flashcards
    }
    fun getOne(index: Int): Flashcard{
        return flashcards[index]
    }
}