package com.nocox.tgames.updown.usecase

import com.nocox.tgames.updown.domain.*
import com.nocox.tgames.updown.domain.repository.BaseTrumpRepository
import com.nocox.tgames.updown.domain.repository.DrawHistoryRepository

class DrawBaseTrumpUseCase(
    private val baseTrumpRepository: BaseTrumpRepository,
    private val drawHistoryRepository: DrawHistoryRepository
) {
    fun invoke(): BaseTrump {
        val deck = Deck.getCurrentDeck(drawHistoryRepository.findDrawHistory())
        val baseTrump = deck.drawBaseTrump()
        baseTrumpRepository.saveBaseTrump(baseTrump)
        drawHistoryRepository.saveDrawHistory(Draw(baseTrump.trump))
        return baseTrump
    }
}