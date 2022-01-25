package com.example.memorygame.model

data class Card (
    val idCard: Int,
    var isFaceUp: Boolean = false,
    var isMatched: Boolean = false
)