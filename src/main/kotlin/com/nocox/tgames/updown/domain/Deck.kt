package com.nocox.tgames.updown.domain

import com.nocox.tgames.CardNumber
import com.nocox.tgames.PlayingCard
import com.nocox.tgames.Suit

class Deck(val trumps: List<Trump>) {

    fun drawBaseTrump(): BaseTrump {
        val trump = randomDraw()
        return BaseTrump(trump)
    }

    fun drawTargetTrump(): TargetTrump {
        val trump = randomDraw()
        return TargetTrump(trump)
    }

    private fun randomDraw(): Trump {
        return trumps.filter { !it.opened }.random()
    }

    companion object {

        fun getCurrentDeck(drayHistory: DrawHistory): Deck {
            val currentCards = initDeck().filter {
                !drayHistory.contains(it)
            }
            return Deck(currentCards)
        }

        fun initDeck(): List<Trump> {
            return (Suit.values().flatMap { suit ->
                (1..13).map { CardNumber(it) }.map {
                    PlayingCard.CommonCard(it, suit)
                }
            } + PlayingCard.JokerCard + PlayingCard.JokerCard)
                .map { Trump(it, false) }
        }

    }
}