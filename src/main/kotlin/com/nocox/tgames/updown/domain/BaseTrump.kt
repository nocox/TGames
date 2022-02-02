package com.nocox.tgames.updown.domain

import com.nocox.tgames.PlayingCard

class BaseTrump(val trump: Trump) {

    fun compareTargetTrump(targetTrump: TargetTrump): PlayingCard.CardCompareResult {
        val targetTrumpCard = targetTrump.trump.card
        val baseTrumpCard = this.trump.card

        return baseTrumpCard.compare(targetTrumpCard)
    }
}