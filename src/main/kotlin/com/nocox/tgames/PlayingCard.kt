package com.nocox.tgames

import java.lang.IllegalArgumentException


fun showCommon(card: PlayingCard.CommonCard): String {
    return card.suit.toString() + " " + card.number.showCardNumber()
}

sealed class PlayingCard {

    object JokerCard: PlayingCard()

    class CommonCard(
        val number: CardNumber,
        val suit: Suit
    ): PlayingCard()

    fun outStr(): String {
        return when (this) {
            is CommonCard -> showCommon(this)
            is JokerCard -> "Joker!"
        }
    }

    fun compare(draw: PlayingCard): CardCompareResult {
        return when(this) {
            is JokerCard -> compareJokerLeft(draw)
            is CommonCard -> compareCommonLeft(this, draw)
        }
    }

    private fun compareJokerLeft(draw: PlayingCard): CardCompareResult {
        return when(draw) {
            is JokerCard -> CardCompareResult.DRAW
            is CommonCard -> CardCompareResult.DOWN
        }
    }

    private fun compareCommonLeft(base: CommonCard, draw: PlayingCard): CardCompareResult {
        return when(draw) {
            is JokerCard -> CardCompareResult.UP
            is CommonCard -> compareCommonValue(base.number.value, draw.number.value)
        }
    }

    private fun compareCommonValue(base: Int, draw: Int): CardCompareResult{
        return when {
            base == draw -> CardCompareResult.DRAW
            base > draw -> CardCompareResult.DOWN
            base < draw -> CardCompareResult.UP
            else -> throw IllegalArgumentException()
        }
    }

    enum class CardCompareResult {
        UP, DRAW, DOWN;
    }
}

class CardNumber(
    val value: Int
){
    init {
        if (1 > value || 13 < value) {
            throw IllegalArgumentException()
        }
    }

    fun showCardNumber(): String {
        return when(value) {
            1 -> "A"
            11 -> "J"
            12 -> "Q"
            13 -> "K"
            2, 3, 4, 5, 6, 7, 8, 9, 10 -> value.toString()
            else -> throw IllegalArgumentException()
        }
    }
}

enum class Suit {
    SPADE,
    CLUB,
    DIAMOND,
    HEART
}