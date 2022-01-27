package com.example.memorygame.model

import com.example.memorygame.util.DEFAULT_ICONS

class MemoryGame(
    private val boardSize: BoardSize
) {

    private var indexOfSingleSelectedCard: Int? = null
    private var numCardFlips = 0
    val cards: List<Card>
    var numPairsFound = 0

    init {
        val chosenImages = DEFAULT_ICONS.shuffled().take(boardSize.getNumPairs())
        val randomizedImages = (chosenImages + chosenImages).shuffled()
        cards = randomizedImages.map { Card(it) }
    }

    fun flipCard(position: Int): Boolean {
        numCardFlips++
        val card = cards[position]
        var foundMatch = false
        if(indexOfSingleSelectedCard == null){
            restoreCards()
            indexOfSingleSelectedCard = position
        }else{
            foundMatch = checkForMatch(indexOfSingleSelectedCard!!, position)
            indexOfSingleSelectedCard = null
        }
        card.isFaceUp = !card.isFaceUp
        return foundMatch
    }

    private fun restoreCards(){
        for(card in cards){
            if(!card.isMatched)
                card.isFaceUp = false
        }
    }

    private fun checkForMatch(position1: Int, position2: Int): Boolean {
        if(cards[position1].idCard != cards[position2].idCard)
            return false
        cards[position1].isMatched = true
        cards[position2].isMatched = true
        numPairsFound++
        return true
    }

    fun haveWonGame(): Boolean = numPairsFound == boardSize.getNumPairs()

    fun isCardFaceUp(position: Int): Boolean = cards[position].isFaceUp

    fun getNumMoves(): Int = numCardFlips
}