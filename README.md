# Assn5

## Flash Cards:

## Description:
- The user is presented with a flash card.
- The user swipes their finger to the left and the flash card flips over to reveal the back of the flashcard (we will talk about a strategy for that in a minute)
- Once on the back, if the user swipes to the left again then the flash card leaves the screen to the left and the next flash card (if there is one) comes in from the right.
- If the user is on the back of a card and the swipe to the right then the card flips back over.
- If the user is on the front of a card and they swipe to the right, then the current card should leave the screen to the right and the previous card (if there is one) should slide in from the left.


### Requirements:
Main page

    (1.1) Hard Code about 20 flashcards to work with.
    (1.2) Flash cards should flip intuitively both forwards and backward.
    (1.3) When you swipe left on a card that has already been flipped it should slide off the screen and slide the next card on.
    (1.4) Every gesture should have a threshold where if the user stops swiping then it animates back to the appropriate state.
    (1.5) You should be able to swipe back to previous cards. (You can decide whether those cards are flipped or not when you go back to them.)

### Hints:
- Don't forget how LaunchedEffect works! You can set up a side effect to run when your translation value changes.
    * Use LaunchedEffect to perform the swapping of cards when the current card slides off the screen.
- You should only need at most 3 cards on the screen at a time: the current card you are on, the next card (if there is one), and the previous card (if there is one)
    * You do not need to manage the state of any of the cards except the current one! Only the current card needs to rotate and you can use the translation value of the current card to determine the position of the next and previous card. In my example, the next card's X translation is just the current card's X translation + 1000.
    I recommend putting your cards in a Box composable, that way they stack on top of each other and you can just translate the next and previous cards out of the way.
- I recommend putting your cards in a Box, and then putting that Box in a Row. Make it so that the Row has the pointerInput, that way the gestures will work even if the user not directly touching one of the cards.

### Pseudocode:

Models:
    Flashcard:
        answer:string
        question:string

Screens:
    leftCard: Flashcard
    centerCard: Flashcard
    rightCard: Flashcard
    Homescreen:
        displays current flashcard

Repositories:
    getFlashcards: List<Flashcards> // hardcoded flashcards

Components:
    Card:
        display:string

Viewmodels:
    HomeViewModel:
        State:
            flipped: boolean
            dragged: boolean
        getFlashcard

